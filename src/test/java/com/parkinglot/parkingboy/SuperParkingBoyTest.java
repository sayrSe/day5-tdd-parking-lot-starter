package com.parkinglot.parkingboy;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.parkinglot.parkingboy.ParkingBoyTestDataFactory.*;
import static org.junit.jupiter.api.Assertions.*;

class SuperParkingBoyTest {

    @Test
    void should_park_to_parking_lot_with_more_parking_space_when_park_given_a_smart_parking_boy_and_two_parking_lots_with_one_have_larger_available_position_rate() {
        // Given
        ParkingLot parkingLotWithLargerPosRate = buildParkingLotWithThreeCapacityAndOneParkedCar();
        ParkingLot parkingLotWithSmallerPosRate = buildParkingLotWithFiveCapacityAndTwoParkedCar();
        List<ParkingLot> parkingLots = List.of(parkingLotWithLargerPosRate, parkingLotWithSmallerPosRate);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car();

        // When
        ParkingTicket parkingTicket = superParkingBoy.park(car);

        // Then
        assertNotNull(parkingTicket);
        assertEquals(1, parkingLotWithLargerPosRate.getAvailableCapacity());
        assertEquals(3, parkingLotWithSmallerPosRate.getAvailableCapacity());
    }

    @Test
    void should_park_to_first_parking_lot_when_park_given_a_super_parking_boy_and_two_parking_lots_with_equal_available_position_rate() {
        // Given
        List<ParkingLot> parkingLots = buildTwoEmptyParkingLots();
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car();

        // When
        ParkingTicket parkingTicket = superParkingBoy.park(car);

        // Then
        assertNotNull(parkingTicket);
        assertEquals(9, parkingLots.get(0).getAvailableCapacity());
        assertEquals(10, parkingLots.get(1).getAvailableCapacity());
    }

    @Test
    void should_park_to_second_parking_lot_when_park_given_a_smart_parking_boy_and_first_parking_lot_full_and_second_has_available_position_and_a_car() {
        // Given
        ParkingLot firstParkingLot = buildFullParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        // When
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        // Then
        assertNotNull(parkingTicket);
        assertEquals(0, firstParkingLot.getAvailableCapacity());
        assertEquals(9, secondParkingLot.getAvailableCapacity());
    }
}