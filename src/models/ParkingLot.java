package models;

import java.util.List;

import enums.BillCalculatorStrategyType;
import enums.ParkingLotStatus;
import enums.SlotAssignmentStrategyType;
import enums.VehicleType;

public class ParkingLot extends BaseModel {
	
	private String parkingLotName;
	private SlotAssignmentStrategyType slotAssignmentStrategyType;
	private String address;
	private List<ParkingFloor> parkingFloors;
	private List<Gate> gates;
	private List<VehicleType> allowedVehicleType;
	private ParkingLotStatus parkingLotStatus;
	private BillCalculatorStrategyType billCalculatorStrategyType;
	
	public String getParkingLotName() {
		return parkingLotName;
	}

	public void setParkingLotName(String parkingLotName) {
		this.parkingLotName = parkingLotName;
	}

	public SlotAssignmentStrategyType getSlotAssignmentStrategyType() {
		return slotAssignmentStrategyType;
	}

	public void setSlotAssignmentStrategyType(SlotAssignmentStrategyType slotAssignmentStrategyType) {
		this.slotAssignmentStrategyType = slotAssignmentStrategyType;
	}

	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public List<ParkingFloor> getParkingFloors() {
		return parkingFloors;
	}
	
	public void setParkingFloors(List<ParkingFloor> parkingFloors) {
		this.parkingFloors = parkingFloors;
	}
	
	public List<Gate> getGates() {
		return gates;
	}
	
	public void setGates(List<Gate> gates) {
		this.gates = gates;
	}
	
	public List<VehicleType> getAllowedVehicleType() {
		return allowedVehicleType;
	}
	
	public void setAllowedVehicleType(List<VehicleType> allowedVehicleType) {
		this.allowedVehicleType = allowedVehicleType;
	}
	
	public ParkingLotStatus getParkingLotStatus() {
		return parkingLotStatus;
	}
	
	public void setParkingLotStatus(ParkingLotStatus parkingLotStatus) {
		this.parkingLotStatus = parkingLotStatus;
	}

	public BillCalculatorStrategyType getBillCalculatorStrategyType() {
		return billCalculatorStrategyType;
	}

	public void setBillCalculatorStrategyType(BillCalculatorStrategyType billCalculatorStrategyType) {
		this.billCalculatorStrategyType = billCalculatorStrategyType;
	}

}
