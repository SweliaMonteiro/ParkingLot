package services;

import java.util.Date;

import enums.BillCalculatorStrategyType;
import enums.BillStatus;
import exceptions.GateNumberNotFound;
import exceptions.ParkingLotNotFound;
import exceptions.TicketNumberNotFound;
import factories.BillCalculatorStrategyFactory;
import models.Bill;
import models.Gate;
import models.ParkingLot;
import models.Ticket;
import repositories.BillRepository;
import repositories.GateRepository;
import repositories.ParkingLotRepository;
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

	public BillService(ParkingLotRepository parkingLotRepository, TicketRepository ticketRepository, GateRepository gateRepository, BillRepository billRepository) {
		this.parkingLotRepository = parkingLotRepository;
		this.ticketRepository = ticketRepository;
		this.gateRepository = gateRepository;
		this.billRepository = billRepository;
	}


	public Bill generateBill(String parkingLotName, String ticketNumber, int gateNumber) throws ParkingLotNotFound, TicketNumberNotFound, GateNumberNotFound {
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

		// Set bill status to UNPAID as the payment is yet to be done
		bill.setBillStatus(BillStatus.UNPAID);

		// Set bill number for the bill
		bill.setBillNumber("BILL-" + ticketNumber + billNumber);
		billNumber++;

		// Save the bill in the DB, will have Id after saving in the DB
		bill = billRepository.saveBill(bill);

		return bill;
	}

}
