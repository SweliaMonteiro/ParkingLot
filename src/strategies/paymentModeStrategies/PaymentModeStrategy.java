package strategies.paymentModeStrategies;

import enums.PaymentStatus;
import models.Payment;

public interface PaymentModeStrategy {
	
	public PaymentStatus payBill(Payment payment);

}
