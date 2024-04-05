package com.vikram.service;

import com.stripe.exception.StripeException;
import com.vikram.Exception.CartException;
import com.vikram.Exception.OrderException;
import com.vikram.Exception.RestaurantException;
import com.vikram.Exception.UserException;
import com.vikram.model.Order;
import com.vikram.model.PaymentResponse;
import com.vikram.model.User;
import com.vikram.request.CreateOrderRequest;

import java.util.List;

public interface OrderService {

    public PaymentResponse createOrder(CreateOrderRequest order, User user) throws UserException, RestaurantException, CartException, StripeException;

    public Order updateOrder(Long orderId, String orderStatus) throws OrderException;

    public void cancelOrder(Long orderId) throws OrderException;

    public List<Order> getUserOrders(Long userId) throws OrderException;

    public List<Order> getOrdersOfRestaurant(Long restaurantId,String orderStatus) throws OrderException, RestaurantException;


}
