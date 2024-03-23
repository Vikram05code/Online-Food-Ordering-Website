package com.vikram.request;

import com.vikram.model.Address;
import lombok.Data;

@Data
public class CreateOrderRequest {

    private Long restaurantId;

    private Address deliveryAddress;

}
