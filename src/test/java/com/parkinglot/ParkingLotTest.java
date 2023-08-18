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
        Car parkedCar = new Car();
        ParkingTicket parkingTicket = parkingLot.park(parkedCar);

    	// When
        Car car = parkingLot.fetch(parkingTicket);

    	// Then
        assertEquals(parkedCar, car);
    }

    @Test
    void should_return_right_car_when_fetch_car_twice_given_parking_lot_with_two_cars_parked() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car firstParkedCar = new Car();
        ParkingTicket firstParkingTicket = parkingLot.park(firstParkedCar);
        Car secondParkedCar = new Car();
        ParkingTicket secondParkingTicket = parkingLot.park(secondParkedCar);

        // When
        Car firstCar = parkingLot.fetch(firstParkingTicket);
        Car secondCar = parkingLot.fetch(secondParkingTicket);

        // Then
        assertEquals(firstParkedCar, firstCar);
        assertEquals(secondParkedCar, secondCar);
    }
}