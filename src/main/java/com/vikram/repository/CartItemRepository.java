package com.vikram.repository;

import com.vikram.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

//    CartItem findByFoodIsContaining

}
