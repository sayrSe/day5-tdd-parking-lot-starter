package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SmartParkingBoyTest {

    @Test
    void should_park_to_parking_lot_with_more_parking_spaces_when_park_given_a_standard_parking_boy_and_two_parking_lots_with_one_have_more_parking_spaces_and_a_car() {
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
}