package com.vikram.service;

import com.vikram.model.Notification;
import com.vikram.model.Order;
import com.vikram.model.Restaurant;
import com.vikram.model.User;

import java.util.List;

public interface NotificationService {

    public Notification sendOrderStatusNotification(Order order);
    public void sendRestaurantNotification(Restaurant restaurant, String message);
    public void sendPromotionalNotification(User user, String message);

    public List<Notification> findUsersNotification(Long userId);

}
