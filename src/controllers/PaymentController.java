package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.PayBillRequestDTO;
import dto.PayBillResponseDTO;
import enums.PaymentMode;
import enums.PaymentStatus;
import enums.ResponseType;
import models.Payment;
import services.PaymentService;

public class PaymentController {

	private PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public PayBillResponseDTO payBill(PayBillRequestDTO PayBillRequestDTO) {
		PayBillResponseDTO response = new PayBillResponseDTO();

		try {
			List<Payment> payments = paymentService.payBill(PayBillRequestDTO.getBillNumber(), PayBillRequestDTO.getAmountPerPaymentMode());
			Map<PaymentMode, PaymentStatus> paymentStatusPerPaymentMode = new HashMap<PaymentMode, PaymentStatus>();
			for(Payment payment:payments) {
				paymentStatusPerPaymentMode.put(payment.getPaymentMode(), payment.getPaymentStatus());
			}
			response.setPaymentStatusPerPaymentMode(paymentStatusPerPaymentMode);
			response.setResponseStatus(ResponseType.SUCCESS);
		}
		catch(Exception e) {
			response.setResponseStatus(ResponseType.FAILURE);
			response.setResponseMessage(e.getMessage());
		}

		return response;
	}

}
