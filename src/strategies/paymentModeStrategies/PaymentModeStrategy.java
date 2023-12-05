package strategies.paymentModeStrategies;

import enums.PaymentStatus;
import models.Bill;
import models.Ticket;

public interface PaymentModeStrategy {
	
	public PaymentStatus payBill(Ticket ticket, Bill bill);

}
