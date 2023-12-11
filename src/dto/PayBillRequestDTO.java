package dto;

import java.util.Map;

import enums.PaymentMode;

public class PayBillRequestDTO {

	private String billNumber; 
	private Map<PaymentMode, Integer> amountPerPaymentMode;

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public Map<PaymentMode, Integer> getAmountPerPaymentMode() {
		return amountPerPaymentMode;
	}

	public void setAmountPerPaymentMode(Map<PaymentMode, Integer> amountPerPaymentMode) {
		this.amountPerPaymentMode = amountPerPaymentMode;
	}

}
