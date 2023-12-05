package repositories;

import java.util.Map;
import java.util.TreeMap;

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

}
