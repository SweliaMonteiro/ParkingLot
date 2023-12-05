package models;

import java.util.List;

import enums.ParkingFloorStatus;
import enums.VehicleType;

public class ParkingFloor extends BaseModel {
	
	private int floorNumber;
	private List<ParkingSlot> parkingSlots;
	private List<VehicleType> allowedVehicleType;
	private ParkingFloorStatus parkingFloorStatus;
	
	public int getFloorNumber() {
		return floorNumber;
	}
	
	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}
	
	public List<ParkingSlot> getParkingSlots() {
		return parkingSlots;
	}
	
	public void setParkingSlots(List<ParkingSlot> parkingSlots) {
		this.parkingSlots = parkingSlots;
	}
	
	public List<VehicleType> getAllowedVehicleType() {
		return allowedVehicleType;
	}
	
	public void setAllowedVehicleType(List<VehicleType> allowedVehicleType) {
		this.allowedVehicleType = allowedVehicleType;
	}
	
	public ParkingFloorStatus getParkingFloorStatus() {
		return parkingFloorStatus;
	}
	
	public void setParkingFloorStatus(ParkingFloorStatus parkingFloorStatus) {
		this.parkingFloorStatus = parkingFloorStatus;
	}

}
