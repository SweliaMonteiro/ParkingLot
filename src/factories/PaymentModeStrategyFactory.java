package factories;

import enums.PaymentMode;
import strategies.paymentModeStrategies.CashPaymentModeStrategy;
import strategies.paymentModeStrategies.OnlinePaymentModeStrategy;
import strategies.paymentModeStrategies.PaymentModeStrategy;

public class PaymentModeStrategyFactory {

	public static PaymentModeStrategy getPaymentModeStrategy(PaymentMode paymentMode) {

		if(paymentMode.equals(PaymentMode.ONLINE)) {
			return new OnlinePaymentModeStrategy();
		}

		else if(paymentMode.equals(PaymentMode.CASH)) {
			return new CashPaymentModeStrategy();
		}

		return null;
	}

}
