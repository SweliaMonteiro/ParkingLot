package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import enums.BillStatus;
import enums.PaymentMode;
import enums.PaymentStatus;
import exceptions.BillNumberNotFound;
import factories.PaymentModeStrategyFactory;
import models.Bill;
import models.Payment;
import repositories.BillRepository;
import repositories.PaymentRepository;
import strategies.paymentModeStrategies.PaymentModeStrategy;

public class PaymentService {

	private BillRepository billRepository;
	private PaymentRepository paymentRepository;

	public PaymentService(BillRepository billRepository, PaymentRepository paymentRepository) {
		this.billRepository = billRepository;
		this.paymentRepository = paymentRepository;
	}


	public List<Payment> payBill(String billNumber, Map<PaymentMode, Integer> amountPerPaymentMode) throws BillNumberNotFound {
		List<Payment> payments = new ArrayList<Payment>();

		// Get the Bill from the DB for the given bill number
		Bill bill = billRepository.getBillByBillNumber(billNumber);

		// To keep count of successful payments
		int paymentSuccessCount = 0;

		// For each Payment Mode create a Payment object and save in DB
		for(PaymentMode paymentMode:amountPerPaymentMode.keySet()) {
			Payment payment = new Payment();

			// Set the payment mode for the Payment
			payment.setPaymentMode(paymentMode);
			// Set the amount for the Payment
			payment.setAmount(amountPerPaymentMode.get(paymentMode));
			// Set the bill for the Payment
			payment.setBill(bill);

			// Pay the amount using Payment Mode Strategy and set the payment status 
			PaymentModeStrategy paymentModeStrategy = PaymentModeStrategyFactory.getPaymentModeStrategy(paymentMode);
			PaymentStatus paymentStatus = paymentModeStrategy.payBill(payment);
			payment.setPaymentStatus(paymentStatus);

			if(paymentStatus.equals(PaymentStatus.SUCCESS)) {
				paymentSuccessCount++;
			}

			// Save the Payment in the DB, will have Id after saving in the DB
			payment = paymentRepository.savePayment(payment);

			payments.add(payment);
		}

		// If all Payments are successful then Bill is paid
		if(paymentSuccessCount == amountPerPaymentMode.size()) {
			bill.setBillStatus(BillStatus.PAID);
		}
		// If there are no successful payments then Bill is unpaid
		else if(paymentSuccessCount == 0) {
			bill.setBillStatus(BillStatus.UNPAID);
		}
		// Else the Bill is partially paid
		else {
			bill.setBillStatus(BillStatus.PARTIALLY_PAID);
		}

		// Update the Bill with the list of Payments and update the previous record of Bill in DB
		bill.setPayments(payments);
		billRepository.saveBill(bill);

		return payments;
	}

}
