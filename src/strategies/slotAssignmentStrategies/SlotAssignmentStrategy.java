package strategies.slotAssignmentStrategies;

import models.ParkingLot;
import models.ParkingSlot;
import models.Vehicle;

public interface SlotAssignmentStrategy {
	
	public ParkingSlot getSlot(ParkingLot parkingLot, Vehicle vehicle);

}
