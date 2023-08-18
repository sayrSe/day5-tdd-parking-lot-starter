package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    @Test
    void should_return_ticket_when_park_given_parking_lot_a_car() {
    	// Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

    	// When
        ParkingTicket parkingTicket = parkingLot.park(car);

    	// Then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_car_when_fetch_given_parking_lot_with_car_parked() {
    	// Given
        ParkingLot parkingLot = new ParkingLot();
        Car parkedCard = new Car();
        ParkingTicket parkingTicket = parkingLot.park(parkedCard);

    	// When
        Car car = parkingLot.fetch(parkingTicket);

    	// Then
        assertEquals(parkedCard, car);
    }
}