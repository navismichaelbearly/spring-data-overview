package com.pluralsight.springdataoverview;

import com.pluralsight.springdataoverview.entity.Flight;
import com.pluralsight.springdataoverview.repository.FlightRepository;
import com.pluralsight.springdataoverview.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TransactionalTests {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightService flightService;

    @BeforeEach
    public void setUp() {
        flightRepository.deleteAll();
    }

    @Test
    public void shouldNotRollBackWhenThereNoTransaction() {
        try {
            flightService.saveFlight(new Flight());
        } catch (Exception e) {
            // Do nothing
        }
        finally {
            assertThat(flightRepository.findAll())
                    .isNotEmpty();
        }
    }

    @Test
    public void shouldNotRollBackWhenThereIsATransaction() {
        try {
            flightService.saveFlightTransactional(new Flight());
        } catch (Exception e) {
            //Do Nothing
        }
        finally {
            assertThat(flightRepository.findAll())
                    .isEmpty();
        }
    }
}
