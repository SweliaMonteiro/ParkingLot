package models;

import java.util.Date;
import java.util.List;

import enums.BillStatus;

public class Bill extends BaseModel {
	
	private String billNumber; 
	private Ticket ticket;
	private Date exitTime;
	private int amount; 
	private BillStatus billStatus;
	private List<Payment> payments;
	private Gate generatedAt;
	private Operator generatedBy;
	
	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public Ticket getTicket() {
		return ticket;
	}
	
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	public Date getExitTime() {
		return exitTime;
	}
	
	public void setExitTime(Date exitTime) {
		this.exitTime = exitTime;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public BillStatus getBillStatus() {
		return billStatus;
	}
	
	public void setBillStatus(BillStatus billStatus) {
		this.billStatus = billStatus;
	}
	
	public List<Payment> getPayments() {
		return payments;
	}
	
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	
	public Gate getGeneratedAt() {
		return generatedAt;
	}
	
	public void setGeneratedAt(Gate generatedAt) {
		this.generatedAt = generatedAt;
	}
	
	public Operator getGeneratedBy() {
		return generatedBy;
	}
	
	public void setGeneratedBy(Operator generatedBy) {
		this.generatedBy = generatedBy;
	}

}
