package com.pluralsight.springdataoverview;

import com.pluralsight.springdataoverview.entity.Flight;
import com.pluralsight.springdataoverview.repository.FlightRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CrudTests {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void shouldPerformCRUDOperations() {
        final Flight flight = new Flight();
        flight.setOrigin("London");
        flight.setDestination("New York");
        flight.setScheduledAt(LocalDateTime.parse("2011-12-13T12:12:00"));


        flightRepository.save(flight);

        assertThat(flightRepository.findAll())
                .hasSize(1)
                .first()
                .isEqualToComparingFieldByField(flight);

        flightRepository.deleteById(flight.getId());

        assertThat(flightRepository.count()).isZero();

    }

}
