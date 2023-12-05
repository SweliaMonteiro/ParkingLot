package repositories;

import java.util.Map;
import java.util.TreeMap;

import exceptions.ParkingLotNotFound;
import models.ParkingLot;

public class ParkingLotRepository {
	
	// Mocking the DB
	private Map<String, ParkingLot> parkingLotMap = new TreeMap<String, ParkingLot>();
	
	
	public ParkingLot getParkingLotByParkingLotName(String parkingLotName) throws ParkingLotNotFound {
		// Get parking lot details for the given parking lot name from the DB if present in the DB, else throw exception
		if(parkingLotMap.containsKey(parkingLotName)) {
			return parkingLotMap.get(parkingLotName);
		}
			
		throw new ParkingLotNotFound("No Parking Lot found in the DB with the given Parking Lot name.");
	}

}
