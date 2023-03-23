package com.pluralsight.springdataoverview;

import com.pluralsight.springdataoverview.entity.Flight;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class SpringDataOverviewApplicationTests {

	@Autowired
	private EntityManager entityManager;

	@Test
	public void verifyFlightCanBeSaved(){
		final Flight flight = new Flight();
		flight.setOrigin("Chennai");
		flight.setDestination("Delhi");
		flight.setScheduledAt(LocalDateTime.parse("2011-12-13T12:12:00"));

		entityManager.persist(flight);

		final TypedQuery<Flight> results = entityManager.createQuery("SELECT f from Flight f", Flight.class);

		final List<Flight> resultList = results.getResultList();

		assertThat(resultList)
				.hasSize(1)
				.first()
				.isEqualTo(flight);
	}
}
