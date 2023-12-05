package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import enums.BillCalculatorStrategyType;
import enums.BillStatus;
import enums.PaymentMode;
import enums.PaymentStatus;
import exceptions.GateNumberNotFound;
import exceptions.ParkingLotNotFound;
import exceptions.TicketNumberNotFound;
import factories.BillCalculatorStrategyFactory;
import models.Bill;
import models.Gate;
import models.ParkingLot;
import models.Payment;
import models.Ticket;
import repositories.BillRepository;
import repositories.GateRepository;
import repositories.ParkingLotRepository;
import repositories.PaymentRepository;
import repositories.TicketRepository;
import strategies.billCalculatorStrategies.BillCalculatorStrategy;

public class BillService {
	
	private static int billNumber;

	static {
		billNumber = 1;
	}
	
	private ParkingLotRepository parkingLotRepository;
	private TicketRepository ticketRepository;
	private GateRepository gateRepository;
	private BillRepository billRepository;
	private PaymentRepository paymentRepository;
	
	public BillService(ParkingLotRepository parkingLotRepository, TicketRepository ticketRepository, GateRepository gateRepository, BillRepository billRepository, PaymentRepository paymentRepository) {
		this.parkingLotRepository = parkingLotRepository;
		this.ticketRepository = ticketRepository;
		this.gateRepository = gateRepository;
		this.billRepository = billRepository;
		this.paymentRepository = paymentRepository;
	}
	
	
	public Bill payBill(String parkingLotName, String ticketNumber, int gateNumber, List<PaymentMode> paymentModes) throws ParkingLotNotFound, TicketNumberNotFound, GateNumberNotFound {
		Bill bill = new Bill();
		
		// Set ticket for the bill
		Ticket ticket = ticketRepository.getTicketByTicketNumber(ticketNumber);
		bill.setTicket(ticket);
		
		// Set exit time for the bill
		bill.setExitTime(new Date());
		
		// Set the gate for the bill
		Gate gate = gateRepository.getGateByGateNumber(gateNumber);
		bill.setGeneratedAt(gate);
		
		// Set the operator for the bill
		bill.setGeneratedBy(gate.getCurrentOperator());
		
		// Calculate the amount of the bill
		ParkingLot parkingLot = parkingLotRepository.getParkingLotByParkingLotName(parkingLotName);
		BillCalculatorStrategyType billCalculatorStrategyType = parkingLot.getBillCalculatorStrategyType();
		BillCalculatorStrategy billCalculatorStrategy = BillCalculatorStrategyFactory.getBillCalculatorStrategy(billCalculatorStrategyType);
		int amount = billCalculatorStrategy.calculateBill(parkingLot, ticket);
		bill.setAmount(amount);
		
		// Set payments for the bill
		List<Payment> payments = new ArrayList<Payment>();
		int successPaymentCount = 0;
		for(PaymentMode paymentMode:paymentModes) {
			Payment payment = new Payment();
			payment.setAmount(amount);
			payment.setBill(bill);
			payment.setPaymentMode(paymentMode);
			payment.payBill(ticket);
			payment = paymentRepository.savePayment(payment);
			
			if(payment.getPaymentStatus().equals(PaymentStatus.SUCCESS)) {
				successPaymentCount++;
			}
		}
		bill.setPayments(payments);
		
		// Set bill status for the bill
		int totalPayments = payments.size();
		if(successPaymentCount == 0) {
			bill.setBillStatus(BillStatus.UNPAID);
		}
		else if(totalPayments == successPaymentCount) {
			bill.setBillStatus(BillStatus.PAID);
		}
		else {
			bill.setBillStatus(BillStatus.PARTIALLY_PAID);
		}
		
		// Set bill number for the bill
		bill.setBillNumber("BILL-" + ticketNumber + billNumber);
		billNumber++;
		
		// Save the bill in the DB, will have Id after saving in the DB
		bill = billRepository.saveBill(bill);
		
		return bill;
	}

}
