package models;

import enums.PaymentMode;
import enums.PaymentStatus;
import factories.PaymentModeStrategyFactory;
import strategies.paymentModeStrategies.PaymentModeStrategy;

public class Payment extends BaseModel {
	
	private String referenceId;
	private int amount;
	private Bill bill;
	private PaymentMode paymentMode;
	private PaymentStatus paymentStatus;
	
	public void payBill(Ticket ticket) {
		PaymentModeStrategy paymentModeStrategy = PaymentModeStrategyFactory.getPaymentModeStrategy(paymentMode);
		paymentStatus = paymentModeStrategy.payBill(ticket, bill);
	}
	
	public String getReferenceId() {
		return referenceId;
	}
	
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Bill getBill() {
		return bill;
	}
	
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
	public PaymentMode getPaymentMode() {
		return paymentMode;
	}
	
	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
}
