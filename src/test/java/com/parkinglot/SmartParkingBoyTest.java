package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SmartParkingBoyTest {

    @Test
    void should_park_to_parking_lot_with_more_parking_space_when_park_given_a_smart_parking_boy_and_two_parking_lots_with_one_have_more_parking_space_and_a_car() {
        // Given
        ParkingLot parkingLotWithLessSpace = new ParkingLot(5);
        ParkingLot parkingLotWithMoreSpace = new ParkingLot(10);
        List<ParkingLot> parkingLots = List.of(parkingLotWithLessSpace, parkingLotWithMoreSpace);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        // When
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        // Then
        assertNotNull(parkingTicket);
        assertEquals(5, parkingLotWithLessSpace.getAvailableCapacity());
        assertEquals(9, parkingLotWithMoreSpace.getAvailableCapacity());
    }

    @Test
    void should_park_to_first_parking_lot_when_park_given_a_smart_parking_boy_and_two_parking_lots_with_equal_parking_space_and_a_car() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        // When
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        // Then
        assertNotNull(parkingTicket);
        assertEquals(9, firstParkingLot.getAvailableCapacity());
        assertEquals(10, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_to_second_parking_lot_when_park_given_a_smart_parking_boy_and_first_parking_lot_full_and_second_has_available_position_and_a_car() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        Car parkedCar = new Car();
        firstParkingLot.park(parkedCar);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        // When
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        // Then
        assertNotNull(parkingTicket);
        assertEquals(0, firstParkingLot.getAvailableCapacity());
        assertEquals(1, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_return_right_car_when_fetch_given_a_smart_parking_boy_and_two_parking_lots_both_have_parked_car_and_parking_ticket() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        Car firstParkedCar = new Car();
        ParkingTicket firstParkingTicket = firstParkingLot.park(firstParkedCar);
        Car secondParkedCar = new Car();
        ParkingTicket secondParkingTicket = secondParkingLot.park(secondParkedCar);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        // When
        Car firstFetchedCar = smartParkingBoy.fetch(firstParkingTicket);
        Car secondFetchedCar = smartParkingBoy.fetch(secondParkingTicket);

        // Then
        assertEquals(firstParkedCar, firstFetchedCar);
        assertEquals(secondParkedCar, secondFetchedCar);
    }

    @Test
    void should_return_unrecognizedTicketException_when_fetch_given_a_smart_parking_boy_and_two_parking_lots_and_unrecognized_ticket() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        smartParkingBoy.park(car);
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        // When
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () ->
                smartParkingBoy.fetch(unrecognizedParkingTicket));
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_unrecognizedTicketException_when_fetch_given_a_smart_parking_boy_and_two_parking_lots_and_used_parking_ticket() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        ParkingTicket usedParkingTicket = smartParkingBoy.park(car);
        smartParkingBoy.fetch(usedParkingTicket);

        // When
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () ->
                smartParkingBoy.fetch(usedParkingTicket));
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_noPositionException_when_park_given_a_smart_parking_boy_and_two_parking_lots_both_with_1_capacity() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        Car firstParkedCar = new Car();
        firstParkingLot.park(firstParkedCar);
        Car secondParkedCar = new Car();
        secondParkingLot.park(secondParkedCar);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        // When
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () ->
                smartParkingBoy.park(car));
        assertEquals("No available position.", noAvailablePositionException.getMessage());
    }
}