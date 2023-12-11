package repositories;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import models.Vehicle;

public class VehicleRepository {

	// Mocking the DB
	private Map<String, Vehicle> vehiclesMap = new TreeMap<String, Vehicle>();


	public Optional<Vehicle> getVehicleByVehicleNumber(String vehicleNumber) {
		// Get vehicle details for the given vehicle number from the DB if present in the DB
		for(String vehicleNum:vehiclesMap.keySet()) {
			if(vehicleNum.equals(vehicleNumber)) {
				return Optional.of(vehiclesMap.get(vehicleNum));
			}
		}
		return Optional.empty();
	}


	public Vehicle saveVehicle(Vehicle vehicle) {
		// Save the new vehicle details in the DB
		vehiclesMap.put(vehicle.getVehicleNumber(), vehicle);
		// Set the Id from the DB for the new Vehicle object created
		vehicle.setId(vehiclesMap.get(vehicle.getVehicleNumber()).getId()); 
		return vehicle;
	}

}
