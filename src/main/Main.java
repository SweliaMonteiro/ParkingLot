package main;

import java.util.List;

import controllers.BillController;
import controllers.TicketController;
import dto.IssueTicketRequestDTO;
import dto.IssueTicketResponseDTO;
import dto.PayBillRequestDTO;
import dto.PayBillResponseDTO;
import enums.PaymentMode;
import enums.ResponseType;
import enums.VehicleType;
import repositories.BillRepository;
import repositories.GateRepository;
import repositories.ParkingLotRepository;
import repositories.PaymentRepository;
import repositories.TicketRepository;
import repositories.VehicleRepository;
import services.BillService;
import services.TicketService;

public class Main {
	
	public static void main(String[] args) {
		
		// To issue a ticket
		
		ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
		VehicleRepository vehicleRepository = new VehicleRepository();
		GateRepository gateRepository = new GateRepository();
		TicketRepository ticketRepository = new TicketRepository();
		
		TicketService ticketService = new TicketService(parkingLotRepository, vehicleRepository, gateRepository, ticketRepository);
		
		TicketController ticketController = new TicketController(ticketService);
		
		IssueTicketRequestDTO issueTicketRequestDTO = new IssueTicketRequestDTO();
		issueTicketRequestDTO.setParkingLotName("Mountain View");
		issueTicketRequestDTO.setVehicleNumber("ABC-123");
		issueTicketRequestDTO.setVehicleOwnerName("John");
		issueTicketRequestDTO.setVehicleType(VehicleType.CAR);
		issueTicketRequestDTO.setGateNumber(4);
		
		IssueTicketResponseDTO issueTicketResponseDTO = ticketController.issueTicket(issueTicketRequestDTO);
		
		if(issueTicketResponseDTO.getResponseStatus().equals(ResponseType.SUCCESS)) {
			System.out.println("Ticket issued for " + issueTicketRequestDTO.getVehicleOwnerName() + " : " + issueTicketResponseDTO.getTicketNumber());
		}
		else {
			System.out.println(issueTicketResponseDTO.getResponseMessage());
		}
		
		
		// To pay the bill
		
		BillRepository billRepository = new BillRepository();
		PaymentRepository paymentRepository = new PaymentRepository();
		
		BillService billService = new BillService(parkingLotRepository, ticketRepository, gateRepository, billRepository, paymentRepository);
		
		BillController billController = new BillController(billService);
		
		PayBillRequestDTO payBillRequestDTO = new PayBillRequestDTO();
		payBillRequestDTO.setTicketNumber("TICKET-2");
		payBillRequestDTO.setGateNumber(3);
		payBillRequestDTO.setPaymentModes(List.of(PaymentMode.CASH));
		payBillRequestDTO.setParkingLotName("Mountain View");
		
		PayBillResponseDTO payBillResponseDTO = billController.payBill(payBillRequestDTO);
		
		if(payBillResponseDTO.getResponseStatus().equals(ResponseType.SUCCESS)) {
			System.out.println("Bill amount to be paid for ticket " + payBillRequestDTO.getTicketNumber() + " : " + payBillResponseDTO.getAmount());
		}
		else {
			System.out.println(payBillResponseDTO.getResponseMessage());
		}
		
	}

}
