package dto;

import enums.ResponseType;

public class PayBillResponseDTO {
	
	private ResponseType responseStatus;
	private String responseMessage;
	private String billNumber;
	private int amount;

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

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
