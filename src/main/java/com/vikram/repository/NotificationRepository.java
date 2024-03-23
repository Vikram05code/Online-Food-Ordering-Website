package com.vikram.repository;

import com.vikram.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    public List<Notification> findByCustomerId(Long userId);
    public List<Notification> findByRestaurantId(Long restaurantId);


}
