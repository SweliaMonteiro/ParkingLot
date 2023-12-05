package dto;

import java.util.List;

import enums.PaymentMode;

public class PayBillRequestDTO {
	
	private String ticketNumber;
	private int gateNumber;
	private List<PaymentMode> paymentModes;
	private String parkingLotName;

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public int getGateNumber() {
		return gateNumber;
	}
	
	public void setGateNumber(int gateNumber) {
		this.gateNumber = gateNumber;
	}

	public List<PaymentMode> getPaymentModes() {
		return paymentModes;
	}

	public void setPaymentModes(List<PaymentMode> paymentModes) {
		this.paymentModes = paymentModes;
	}

	public String getParkingLotName() {
		return parkingLotName;
	}

	public void setParkingLotName(String parkingLotName) {
		this.parkingLotName = parkingLotName;
	}

}
