package com.vikram.controller;

import com.stripe.exception.StripeException;
import com.vikram.model.Order;
import com.vikram.model.PaymentResponse;
import com.vikram.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

	@PostMapping("/payment")
	public ResponseEntity<PaymentResponse> generatePaymentLink(@RequestBody Order order)
			throws StripeException {

		PaymentResponse res = paymentService.generatePaymentLink(order);

		return new ResponseEntity<PaymentResponse>(res, HttpStatus.ACCEPTED);
	}

}