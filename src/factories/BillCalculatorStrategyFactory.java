package factories;

import enums.BillCalculatorStrategyType;
import strategies.billCalculatorStrategies.BillCalculatorStrategy;
import strategies.billCalculatorStrategies.TotalDurationBillCalculatorStrategy;
import strategies.billCalculatorStrategies.VehicleTypeBillCalculatorStrategy;

public class BillCalculatorStrategyFactory {

	public static BillCalculatorStrategy getBillCalculatorStrategy(BillCalculatorStrategyType billCalculatorStrategyType) {

		if(billCalculatorStrategyType.equals(BillCalculatorStrategyType.TOTAL_DURATION)) {
			return new TotalDurationBillCalculatorStrategy();
		}

		else if(billCalculatorStrategyType.equals(BillCalculatorStrategyType.VEHICLE_TYPE)) {
			return new VehicleTypeBillCalculatorStrategy();
		}

		return null;
	}

}
