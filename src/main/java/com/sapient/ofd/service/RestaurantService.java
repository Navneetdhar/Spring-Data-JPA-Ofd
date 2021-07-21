package com.sapient.ofd.service;

import java.time.LocalDate;
import java.util.List;

import com.sapient.ofd.entity.Restaurant;
import com.sapient.ofd.exception.FoodDeliveryException;

public interface RestaurantService {
	public abstract Restaurant findRestaurantById(Integer restaurantId) throws FoodDeliveryException;
	public abstract List<Restaurant> findRestaurants() throws FoodDeliveryException;
	public abstract Restaurant addRestaurant(Restaurant restaurant) throws FoodDeliveryException;
	public abstract Integer deleteRestaurant(Integer restaurantID) throws FoodDeliveryException;
	public abstract Restaurant updateRestaurant(Restaurant restaurant) throws FoodDeliveryException;
	List<Restaurant> findRestaurantsEstablishedAfter(LocalDate estDate) throws FoodDeliveryException;
	

}
