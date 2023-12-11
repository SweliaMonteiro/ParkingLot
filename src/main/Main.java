package main;

import java.util.Map;

import controllers.BillController;
import controllers.PaymentController;
import controllers.TicketController;
import dto.GenerateBillRequestDTO;
import dto.GenerateBillResponseDTO;
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
import services.PaymentService;
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


		// To generate the bill

		BillRepository billRepository = new BillRepository();

		BillService billService = new BillService(parkingLotRepository, ticketRepository, gateRepository, billRepository);

		BillController billController = new BillController(billService);

		GenerateBillRequestDTO generateBillRequestDTO = new GenerateBillRequestDTO();
		generateBillRequestDTO.setTicketNumber("TICKET-2");
		generateBillRequestDTO.setGateNumber(3);
		generateBillRequestDTO.setParkingLotName("Mountain View");

		GenerateBillResponseDTO generateBillResponseDTO = billController.generateBill(generateBillRequestDTO);

		if(generateBillResponseDTO.getResponseStatus().equals(ResponseType.SUCCESS)) {
			System.out.println("Bill amount to be paid for ticket " + generateBillRequestDTO.getTicketNumber() + " : " + generateBillResponseDTO.getAmount());
		}
		else {
			System.out.println(generateBillResponseDTO.getResponseMessage());
		}


		// To pay the bill

		PaymentRepository paymentRepository = new PaymentRepository();

		PaymentService paymentService = new PaymentService(billRepository, paymentRepository);

		PaymentController paymentController = new PaymentController(paymentService);

		PayBillRequestDTO payBillRequestDTO = new PayBillRequestDTO();
		payBillRequestDTO.setBillNumber("BILL-1");
		payBillRequestDTO.setAmountPerPaymentMode(Map.of(PaymentMode.CASH, 200, PaymentMode.ONLINE, 100));

		PayBillResponseDTO payBillResponseDTO = paymentController.payBill(payBillRequestDTO);

		if(payBillResponseDTO.getResponseStatus().equals(ResponseType.SUCCESS)) {
			System.out.println("Please find below the Payment Status for each Payment Modes:");
			for(PaymentMode paymentMode:payBillResponseDTO.getPaymentStatusPerPaymentMode().keySet()) {
				System.out.println(paymentMode + " : " + payBillResponseDTO.getPaymentStatusPerPaymentMode().get(paymentMode));
			}
		}
		else {
			System.out.println(payBillResponseDTO.getResponseMessage());
		}

	}

}
