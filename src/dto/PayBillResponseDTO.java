package dto;

import java.util.Map;

import enums.PaymentMode;
import enums.PaymentStatus;
import enums.ResponseType;

public class PayBillResponseDTO {

	private ResponseType responseStatus;
	private String responseMessage;
	private Map<PaymentMode, PaymentStatus> paymentStatusPerPaymentMode;

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

	public Map<PaymentMode, PaymentStatus> getPaymentStatusPerPaymentMode() {
		return paymentStatusPerPaymentMode;
	}

	public void setPaymentStatusPerPaymentMode(Map<PaymentMode, PaymentStatus> paymentStatusPerPaymentMode) {
		this.paymentStatusPerPaymentMode = paymentStatusPerPaymentMode;
	}

}
