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
        List<ParkingLot> assignedParkingLots = List.of(manager.getParkingLots().get(0));
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(assignedParkingLots);

        // When
        manager.addToManagement(standardParkingBoy);

        // Then
        assertTrue(manager.getManagement().contains(standardParkingBoy));
    }

    @Test
    void should_park_to_first_parking_lot_when_park_with_parking_boy_given_parking_lot_service_manager_and_standard_parking_boy_in_management_and_two_parking_lots_and_car() {
        // Given
        ParkingLotServiceManager manager = new ParkingLotServiceManager(buildTwoEmptyParkingLots());
        List<ParkingLot> assignedParkingLots = manager.getParkingLots();
        Car car = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(assignedParkingLots);
        manager.addToManagement(standardParkingBoy);

        // When
        ParkingTicket parkingTicket = manager.parkWithParkingBoy(standardParkingBoy, car);

        // Then
        assertNotNull(parkingTicket);
        assertEquals(9, assignedParkingLots.get(0).getAvailableCapacity());
        assertEquals(10, assignedParkingLots.get(1).getAvailableCapacity());
    }
    
    @Test
    void should_return_right_car_when_fetch_with_parking_boy_given_parking_lot_service_manager_and_a_standard_parking_boy_and_two_parking_lots_both_have_parked_car_and_parking_ticket() {
    	// Given
        ParkingLot firstParkingLot = new ParkingLot();
        Car firstParkedCar = new Car();
        ParkingTicket firstParkingTicket = firstParkingLot.park(firstParkedCar);
        ParkingLot secondParkingLot = new ParkingLot();
        Car secondParkedCar = new Car();
        ParkingTicket secondParkingTicket = secondParkingLot.park(secondParkedCar);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);

        ParkingLotServiceManager manager = new ParkingLotServiceManager(parkingLots);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        manager.addToManagement(standardParkingBoy);

    	// When
        Car firstFetchedCar = manager.fetchWithParkingBoy(standardParkingBoy, firstParkingTicket);
        Car secondFetchedCar = manager.fetchWithParkingBoy(standardParkingBoy, secondParkingTicket);

        // Then
        assertEquals(firstParkedCar, firstFetchedCar);
        assertEquals(secondParkedCar, secondFetchedCar);
    }
}