package com.sapient.ofd.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sapient.ofd.entity.Restaurant;

public interface OnlineFoodDeliveryRepository 
				extends JpaRepository<Restaurant,Integer> {
	//custom querry methods
	List<Restaurant> findByAddress(String address);
	List<Restaurant> findTop5ByRating(Integer rating);
	List<Restaurant> findByEstablishedDateBetween(LocalDate start, LocalDate end);
	//Named querry methods
	@Query("select r from Restaurant r where establishedDate> :givenDate")
	List<Restaurant> findRestaurantsEstablishedAfter(@Param("givenDate") LocalDate estDate);
}
