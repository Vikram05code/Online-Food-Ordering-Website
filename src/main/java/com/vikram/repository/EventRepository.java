package com.vikram.repository;

import com.vikram.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    public List<Event> findEventsByRestaurantId(Long id);

}
