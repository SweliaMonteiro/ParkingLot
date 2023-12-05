package controllers;

import dto.IssueTicketRequestDTO;
import dto.IssueTicketResponseDTO;
import enums.ResponseType;
import models.Ticket;
import services.TicketService;

public class TicketController {
	
	private TicketService ticketService;
	
	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	
	public IssueTicketResponseDTO issueTicket(IssueTicketRequestDTO request) {
		IssueTicketResponseDTO response = new IssueTicketResponseDTO();
		
		try {
			Ticket ticket = ticketService.issueTicket(request.getParkingLotName(), request.getVehicleNumber(), 
				                                      request.getVehicleOwnerName(), request.getVehicleType(), request.getGateNumber());
			response.setTicketNumber(ticket.getTicketNumber());
			response.setParkingSlot(ticket.getParkingSlot());
			response.setResponseStatus(ResponseType.SUCCESS);
		}
		catch(Exception e) {
			response.setResponseStatus(ResponseType.FAILURE);
			response.setResponseMessage(e.getMessage());
		}
		
		return response;
	}

}
