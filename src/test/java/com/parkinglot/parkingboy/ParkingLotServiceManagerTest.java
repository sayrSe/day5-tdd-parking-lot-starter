package com.parkinglot.parkingboy;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    void should_park_to_first_parking_lot_when_park_with_parking_boy_given_parking_lot_service_manager_and_standard_parking_boy_in_management_and_two_parking_lots_and_car() {
        // Given
        ParkingLotServiceManager manager = new ParkingLotServiceManager(buildTwoEmptyParkingLots());
        List<ParkingLot> managerParkingLots = manager.getParkingLots();
        StandardParkingBoy smartParkingBoy = new StandardParkingBoy();
        Car car = new Car();
        manager.addToManagement(smartParkingBoy);

        // When
        ParkingTicket parkingTicket = manager.parkWithParkingBoy(smartParkingBoy, car);

        // Then
        assertNotNull(parkingTicket);
        assertEquals(9, managerParkingLots.get(0).getAvailableCapacity());
        assertEquals(10, managerParkingLots.get(1).getAvailableCapacity());
    }
}