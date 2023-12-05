package strategies.billCalculatorStrategies;

import models.ParkingLot;
import models.Ticket;

public interface BillCalculatorStrategy {
	
	public int calculateBill(ParkingLot parkinglot, Ticket ticket);

}
