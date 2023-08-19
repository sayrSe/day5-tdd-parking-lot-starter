package com.parkinglot.parkingboy;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SuperParkingBoyTest {

    @Test
    void should_park_to_parking_lot_with_more_parking_space_when_park_given_a_smart_parking_boy_and_two_parking_lots_with_one_have_larger_available_position_rate() {
        // Given
        ParkingLot parkingLotWithLargerPosRate = new ParkingLot(3);
        Car firstParkedCar = new Car();
        parkingLotWithLargerPosRate.park(firstParkedCar);
        ParkingLot parkingLotWithSmallerPosRate = new ParkingLot(5);
        Car secondParkedCar = new Car();
        Car thirdParkedCar = new Car();
        parkingLotWithSmallerPosRate.park(secondParkedCar);
        parkingLotWithSmallerPosRate.park(thirdParkedCar);

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
}