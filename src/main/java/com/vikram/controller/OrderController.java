package com.vikram.controller;

import com.stripe.exception.StripeException;
import com.vikram.Exception.CartException;
import com.vikram.Exception.OrderException;
import com.vikram.Exception.RestaurantException;
import com.vikram.Exception.UserException;
import com.vikram.model.Order;
import com.vikram.model.PaymentResponse;
import com.vikram.model.User;
import com.vikram.request.CreateOrderRequest;
import com.vikram.service.OrderService;
import com.vikram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody CreateOrderRequest order,
                                                       @RequestHeader("Authorization") String jwt)
            throws UserException, RestaurantException,
            CartException,
            StripeException,
            OrderException {
        User user=userService.findUserProfileByJwt(jwt);
        System.out.println("req user "+user.getEmail());
        if(order!=null) {
            PaymentResponse res = orderService.createOrder(order,user);
            return ResponseEntity.ok(res);

        }else throw new OrderException("Please provide valid request body");

    }



    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getAllUserOrders(@RequestHeader("Authorization") String jwt) throws OrderException, UserException{

        User user=userService.findUserProfileByJwt(jwt);

        if(user.getId()!=null) {
            List<Order> userOrders = orderService.getUserOrders(user.getId());
            return ResponseEntity.ok(userOrders);
        }else {
            return new ResponseEntity<List<Order>>(HttpStatus.BAD_REQUEST);
        }
    }





}
