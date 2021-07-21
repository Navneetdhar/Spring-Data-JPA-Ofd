package com.sapient.ofd.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sapient.ofd.app.SpringDataJpaOfdProjectApplication;
import com.sapient.ofd.entity.Restaurant;



@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringDataJpaOfdProjectApplication.class })
@AutoConfigureTestDatabase(replace=Replace.NONE)
@DataJpaTest
public class OnlineFoodDeliveryRepositoryIntegrationTest {
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private OnlineFoodDeliveryRepository onlineFoodDeliveryRepository;
	
	@Test
	public void whenFindById_thenReturnRestaurant() {
		Restaurant restaurant = new Restaurant(null,"Taj","Continental",LocalTime.parse("08:30:00"),
						LocalTime.parse("22:30:00"),LocalDate.of(2010, 5, 1),5,"Hyderabad",9862012356L);
		
		entityManager.persistAndFlush(restaurant);
		Restaurant restaurantFromDB = onlineFoodDeliveryRepository.findById(restaurant.getRestaurantId()).get();
		assertThat(restaurantFromDB.getRestaurantId()).isEqualTo(restaurant.getRestaurantId());	
	}
	@Test
    public void whenFindByAddress_thenReturnAddress() {
        Restaurant restaurant = new Restaurant(null,"Madhuban","Itialin",LocalTime.parse("10:30:00"),LocalTime.parse("21:00:00"),LocalDate.of(2016, 1, 5),2,"Coimbatore",7896548321L);
        entityManager.persistAndFlush(restaurant);

        List<Restaurant> restaurantList = onlineFoodDeliveryRepository.findByAddress(restaurant.getAddress());
        if(restaurantList.size()!=0) {
        	assertThat(restaurantList.get(0).getAddress()).isEqualTo(restaurant.getAddress());
        }else {
        	assertThat(restaurantList.get(0).getAddress()).isNotEqualTo(restaurant.getAddress());
        }
    }
	@Test
    public void whenInvalidAddress_thenReturnNull() {
    	List<Restaurant> foundList = onlineFoodDeliveryRepository.findByAddress("Invalid Address");
        assertThat(foundList).isEmpty();
    }
	@Test
    public void whenInvalidId_thenReturnNull() {
    	Restaurant fromDb = onlineFoodDeliveryRepository.findById(-11).orElse(null);
        assertThat(fromDb).isNull();
    }

}
