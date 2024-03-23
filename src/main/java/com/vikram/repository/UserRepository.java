package com.vikram.repository;

import com.vikram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


   @Query("SELECT u FROM User u Where u.status='PENDING'")
   public List<User> getPenddingRestaurantOwners();

   public User findByEmail(String userName);

}
