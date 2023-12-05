package dto;

import enums.ResponseType;
import models.ParkingSlot;

public class IssueTicketResponseDTO {
	
	private ResponseType responseStatus;
	private String responseMessage;
	private String ticketNumber;
	private ParkingSlot parkingSlot;
	
	public ResponseType getResponseStatus() {
		return responseStatus;
	}
	
	public void setResponseStatus(ResponseType responseStatus) {
		this.responseStatus = responseStatus;
	}
	
	public String getResponseMessage() {
		return responseMessage;
	}
	
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	public String getTicketNumber() {
		return ticketNumber;
	}
	
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	
	public ParkingSlot getParkingSlot() {
		return parkingSlot;
	}
	
	public void setParkingSlot(ParkingSlot parkingSlot) {
		this.parkingSlot = parkingSlot;
	}

}
