package repositories;

import java.util.Map;
import java.util.TreeMap;

import models.Payment;

public class PaymentRepository {

	// Mocking the DB
	Map<String, Payment> paymentMap = new TreeMap<String, Payment>();


	public Payment savePayment(Payment payment) {
		// Save the new payment details in the DB
		paymentMap.put(payment.getBill().getBillNumber(), payment);
		// Set the Id from the DB for the new Payment object created
		payment.setId(paymentMap.get(payment.getBill().getBillNumber()).getId());
		return payment;
	}

}
