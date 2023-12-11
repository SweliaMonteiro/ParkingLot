package controllers;

import dto.GenerateBillRequestDTO;
import dto.GenerateBillResponseDTO;
import enums.ResponseType;
import models.Bill;
import services.BillService;

public class BillController {

	BillService billService;

	public BillController(BillService billService) {
		this.billService = billService;
	}

	public GenerateBillResponseDTO generateBill(GenerateBillRequestDTO request) {
		GenerateBillResponseDTO response = new GenerateBillResponseDTO();

		try {
			Bill bill = billService.generateBill(request.getParkingLotName(), request.getTicketNumber(), request.getGateNumber());
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
