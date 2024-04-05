package com.vikram.service;

import com.stripe.exception.StripeException;
import com.vikram.model.Order;
import com.vikram.model.PaymentResponse;

public interface PaymentService {

    public PaymentResponse generatePaymentLink(Order order) throws StripeException;

}
