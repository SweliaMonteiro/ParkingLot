package controllers;

import dto.PayBillRequestDTO;
import dto.PayBillResponseDTO;
import enums.ResponseType;
import models.Bill;
import services.BillService;

public class BillController {
	
	BillService billService;
	
	public BillController(BillService billService) {
		this.billService = billService;
	}
	
	public PayBillResponseDTO payBill(PayBillRequestDTO request) {
		PayBillResponseDTO response = new PayBillResponseDTO();
		
		try {
			Bill bill = billService.payBill(request.getParkingLotName(), request.getTicketNumber(), request.getGateNumber(), request.getPaymentModes());
			response.setBillNumber(bill.getBillNumber());
			response.setAmount(bill.getAmount());
			response.setResponseStatus(ResponseType.SUCCESS);
		}
		catch(Exception e) {
			response.setResponseStatus(ResponseType.FAILURE);
			response.setResponseMessage(e.getMessage());
		}
		
		return response;
	}

}
