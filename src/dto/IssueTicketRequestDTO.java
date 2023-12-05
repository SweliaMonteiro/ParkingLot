package dto;

import enums.VehicleType;

public class IssueTicketRequestDTO {
	
	private String parkingLotName;
	private String vehicleNumber;
	private String vehicleOwnerName;
	private VehicleType vehicleType;
	private int gateNumber;
	
	public String getParkingLotName() {
		return parkingLotName;
	}

	public void setParkingLotName(String parkingLotName) {
		this.parkingLotName = parkingLotName;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}
	
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	
	public String getVehicleOwnerName() {
		return vehicleOwnerName;
	}
	
	public void setVehicleOwnerName(String vehicleOwnerName) {
		this.vehicleOwnerName = vehicleOwnerName;
	}
	
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	public int getGateNumber() {
		return gateNumber;
	}
	
	public void setGateNumber(int gateNumber) {
		this.gateNumber = gateNumber;
	}
	
}
