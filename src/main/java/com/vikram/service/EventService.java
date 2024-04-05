package com.vikram.service;

import com.vikram.Exception.RestaurantException;

import com.vikram.model.Events;

import java.util.List;

public interface EventService {

    public Events createEvent(Events event, Long restaurantId) throws RestaurantException;

    public List<Events> findAllEvent();

    public List<Events> findRestaurantsEvent(Long id);

    public void deleteEvent(Long id) throws Exception;

    public Events findById(Long id) throws Exception;

}
