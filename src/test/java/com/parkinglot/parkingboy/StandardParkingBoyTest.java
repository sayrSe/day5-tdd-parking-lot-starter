package com.parkinglot.parkingboy;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.parkinglot.parkingboy.ParkingBoyTestDataFactory.*;
import static org.junit.jupiter.api.Assertions.*;

class StandardParkingBoyTest {

    @Test
    void should_park_to_first_parking_lot_when_park_given_a_standard_parking_boy_and_two_parking_lots_and_a_car() {
        // Given
        List<ParkingLot> parkingLots = buildTwoEmptyParkingLots();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car();

        // When
        ParkingTicket parkingTicket = standardParkingBoy.park(car);

        // Then
        assertNotNull(parkingTicket);
        assertEquals(9, parkingLots.get(0).getAvailableCapacity());
        assertEquals(10, parkingLots.get(1).getAvailableCapacity());
    }

    @Test
    void should_park_to_second_parking_lot_when_park_given_a_standard_parking_boy_and_first_parking_lot_full_and_second_has_available_position_and_a_car() {
        // Given
        ParkingLot firstParkingLot = buildFullParkingLot();
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car();

        // When
        ParkingTicket parkingTicket = standardParkingBoy.park(car);

        // Then
        assertNotNull(parkingTicket);
        assertEquals(0, firstParkingLot.getAvailableCapacity());
        assertEquals(1, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_return_right_car_when_fetch_given_a_standard_parking_boy_and_two_parking_lots_both_have_parked_car_and_parking_ticket() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        Car firstParkedCar = new Car();
        ParkingTicket firstParkingTicket = firstParkingLot.park(firstParkedCar);
        Car secondParkedCar = new Car();
        ParkingTicket secondParkingTicket = secondParkingLot.park(secondParkedCar);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);

        // When
        Car firstFetchedCar = standardParkingBoy.fetch(firstParkingTicket);
        Car secondFetchedCar = standardParkingBoy.fetch(secondParkingTicket);

        // Then
        assertEquals(firstParkedCar, firstFetchedCar);
        assertEquals(secondParkedCar, secondFetchedCar);
    }

    @Test
    void should_return_unrecognizedTicketException_when_fetch_given_a_standard_parking_boy_and_two_parking_lots_and_unrecognized_ticket() {
        // Given
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(buildTwoEmptyParkingLots());
        Car car = new Car();
        standardParkingBoy.park(car);
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        // When
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () ->
                standardParkingBoy.fetch(unrecognizedParkingTicket));
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_unrecognizedTicketException_when_fetch_given_a_standard_parking_boy_and_two_parking_lots_and_used_parking_ticket() {
        // Given
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(buildTwoEmptyParkingLots());
        Car car = new Car();
        ParkingTicket usedParkingTicket = standardParkingBoy.park(car);
        standardParkingBoy.fetch(usedParkingTicket);

        // When
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () ->
                standardParkingBoy.fetch(usedParkingTicket));
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_noPositionException_when_park_given_a_standard_parking_boy_and_two_parking_lots_both_with_full_capacity() {
        // Given
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(buildTwoFullParkingLots());
        Car car = new Car();

        // When
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () ->
                standardParkingBoy.park(car));
        assertEquals("No available position.", noAvailablePositionException.getMessage());
    }
}