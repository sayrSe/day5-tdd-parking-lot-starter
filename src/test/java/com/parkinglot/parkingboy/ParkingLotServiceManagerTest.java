package com.parkinglot.parkingboy;

import org.junit.jupiter.api.Test;

import static com.parkinglot.parkingboy.ParkingBoyTestDataFactory.buildTwoEmptyParkingLots;
import static org.junit.jupiter.api.Assertions.*;

class ParkingLotServiceManagerTest {

    @Test
    void should_add_parking_boy_to_management_when_add_to_management_given_parking_lot_service_manager_and_standard_parking_boy_and_two_parking_lots() {
    	// Given
        ParkingLotServiceManager manager = new ParkingLotServiceManager(buildTwoEmptyParkingLots());
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();

    	// When
        manager.addToManagement(standardParkingBoy);

    	// Then
        assertTrue(manager.getManagement().contains(standardParkingBoy));
    }
}