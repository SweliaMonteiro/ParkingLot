package repositories;

import java.util.Map;
import java.util.TreeMap;

import exceptions.BillNumberNotFound;
import models.Bill;

public class BillRepository {

	// Mocking the DB
	Map<String, Bill> billMap = new TreeMap<String, Bill>();


	public Bill saveBill(Bill bill) {
		// Save the new bill details in the DB
		billMap.put(bill.getBillNumber(), bill);
		// Set the Id from the DB for the new Bill object created
		bill.setId(billMap.get(bill.getBillNumber()).getId());
		return bill;
	}


	public Bill getBillByBillNumber(String billNumber) throws BillNumberNotFound {
		// Get bill details for the given bill number from the DB if present in the DB, else throw exception
		if(billMap.containsKey(billNumber)) {
			return billMap.get(billNumber);
		}

		throw new BillNumberNotFound("No Bill found in the DB with the given Bill number.");
	}

}
