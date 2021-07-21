package com.sapient.ofd.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.ofd.dao.OnlineFoodDeliveryRepository;

import com.sapient.ofd.entity.Restaurant;
import com.sapient.ofd.exception.FoodDeliveryException;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
	@Autowired
	private OnlineFoodDeliveryRepository ofdRepo;
	
	@Override
	public Restaurant findRestaurantById(Integer restaurantId) throws FoodDeliveryException {
		try {
			Optional<Restaurant> optional = ofdRepo.findById(restaurantId);
			if(optional.isPresent())
				return optional.get();
			else
				throw new PersistenceException("Invalid Id");
		}catch(DataAccessException e) {
			throw new FoodDeliveryException(e.getMessage(),e);
		}
	}

	@Override
	public List<Restaurant> findRestaurants() throws FoodDeliveryException {
		try {
			List<Restaurant> restaurantList = ofdRepo.findAll();
			//bussiness rules
			return restaurantList;
		}catch(DataAccessException e) {
			throw new FoodDeliveryException(e.getMessage(),e);
		}
	}

	@Override
	public Restaurant addRestaurant(Restaurant restaurant) throws FoodDeliveryException {
		try {
			//validations
			RestaurantValidator validator = new RestaurantValidator();
			if(validator.isValidEstablishedDate(restaurant) &&
						validator.isValidRating(restaurant)) {
				restaurant.setRestaurantId(null);
				return ofdRepo.save(restaurant);
				
			}else {
				throw new FoodDeliveryException("Restaurant established date is invalid");
			}
			//bussiness rules
			
		}catch(DataAccessException e) {
			throw new FoodDeliveryException(e.getMessage(),e);
		}
	}

	@Override
	public Integer deleteRestaurant(Integer restaurantID) throws FoodDeliveryException {
		try {
			ofdRepo.deleteById(restaurantID);
			return restaurantID;
		}catch(DataAccessException e) {
			throw new FoodDeliveryException(e.getMessage(),e);
		}
	}

	@Override
	public Restaurant updateRestaurant(Restaurant restaurant) throws FoodDeliveryException {
		try {
			return ofdRepo.save(restaurant);
		}catch(DataAccessException e) {
			throw new FoodDeliveryException(e.getMessage(),e);
		}
	}
	
	
	@Override
	public List<Restaurant> findRestaurantsEstablishedAfter(LocalDate estDate) throws FoodDeliveryException{
		try {
			return ofdRepo.findRestaurantsEstablishedAfter(estDate);		

		}catch(DataAccessException e) {
			throw new FoodDeliveryException(e.getMessage(),e);
		}
	}


}
