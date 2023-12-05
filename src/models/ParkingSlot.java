package models;

import enums.ParkingSlotStatus;
import enums.VehicleType;

public class ParkingSlot extends BaseModel {
	
	private int slotNumber;
	private ParkingFloor parkingFloor;
	private VehicleType vehicleType; 
	private ParkingSlotStatus parkingSlotStatus;
	
	public int getSlotNumber() {
		return slotNumber;
	}
	
	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}
	
	public ParkingFloor getParkingFloor() {
		return parkingFloor;
	}
	
	public void setParkingFloor(ParkingFloor parkingFloor) {
		this.parkingFloor = parkingFloor;
	}
	
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	public ParkingSlotStatus getParkingSlotStatus() {
		return parkingSlotStatus;
	}
	
	public void setParkingSlotStatus(ParkingSlotStatus parkingSlotStatus) {
		this.parkingSlotStatus = parkingSlotStatus;
	}
	
}
