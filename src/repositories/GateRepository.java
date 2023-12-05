package repositories;

import java.util.Map;
import java.util.TreeMap;

import exceptions.GateNumberNotFound;
import models.Gate;

public class GateRepository {
	
	// Mocking the DB
	private Map<Integer, Gate> gatesMap = new TreeMap<Integer, Gate>();
	
	
	public Gate getGateByGateNumber(int gateNumber) throws GateNumberNotFound {
		// Get gate details for the given gate number from the DB if present in the DB, else throw exception
		if(gatesMap.containsKey(gateNumber)) {
			return gatesMap.get(gateNumber);
		}
			
		throw new GateNumberNotFound("No Gate found in the DB with the given Gate number.");
	}

}
