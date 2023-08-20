package com.parkinglot.members;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.exception.FailedToDoOperationException;
import com.parkinglot.service.StandardService;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager {

    private final List<StandardParkingBoy> managedParkingBoys = new ArrayList<>();
    private final List<ParkingLot> parkingLots;

    public ParkingLotServiceManager(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        return new StandardService().park(car, parkingLots);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return new StandardService().fetch(parkingTicket, parkingLots);
    }

    public List<StandardParkingBoy> getManagedParkingBoys() {
        return managedParkingBoys;
    }

    public void addToManagement(StandardParkingBoy standardParkingBoy) {
        managedParkingBoys.add(standardParkingBoy);
    }

    public ParkingTicket parkWithParkingBoy(StandardParkingBoy standardParkingBoy, Car car) {
        if (isParkingBoyAssignedToAnyManagedParkingLot(standardParkingBoy)) {
            throw new FailedToDoOperationException();
        }

        return managedParkingBoys.stream()
                .filter(standardParkingBoy::equals)
                .findFirst()
                .orElseThrow(FailedToDoOperationException::new)
                .park(car);
    }

    public Car fetchWithParkingBoy(StandardParkingBoy standardParkingBoy, ParkingTicket parkingTicket) {
        if (isParkingBoyAssignedToAnyManagedParkingLot(standardParkingBoy)) {
            throw new FailedToDoOperationException();
        }

        return managedParkingBoys.stream()
                .filter(standardParkingBoy::equals)
                .findFirst()
                .orElseThrow(FailedToDoOperationException::new)
                .fetch(parkingTicket);
    }

    private boolean isParkingBoyAssignedToAnyManagedParkingLot(StandardParkingBoy standardParkingBoy) {
        return standardParkingBoy.getParkingLots().stream().noneMatch(parkingLots::contains);
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
