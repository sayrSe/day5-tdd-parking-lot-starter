package com.parkinglot.members;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.exception.FailedToDoOperationException;
import com.parkinglot.exception.UnrecognizedTicketException;
import com.parkinglot.service.StandardParking;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager {

    private final List<ParkingBoy> managedParkingBoys = new ArrayList<>();
    private final List<ParkingLot> parkingLots;

    public ParkingLotServiceManager(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        return new StandardParking().park(car, parkingLots);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.isTicketForCarInParkingLot(parkingTicket))
                .findFirst()
                .orElseThrow(UnrecognizedTicketException::new)
                .fetch(parkingTicket);

    }

    public List<ParkingBoy> getManagedParkingBoys() {
        return managedParkingBoys;
    }

    public void addToManagement(ParkingBoy parkingBoy) {
        managedParkingBoys.add(parkingBoy);
    }

    public ParkingTicket parkWithParkingBoy(ParkingBoy parkingBoy, Car car) {
        if (isParkingBoyAssignedToAnyManagedParkingLot(parkingBoy)) {
            throw new FailedToDoOperationException();
        }

        return managedParkingBoys.stream()
                .filter(parkingBoy::equals)
                .findFirst()
                .orElseThrow(FailedToDoOperationException::new)
                .park(car);
    }

    public Car fetchWithParkingBoy(ParkingBoy parkingBoy, ParkingTicket parkingTicket) {
        if (isParkingBoyAssignedToAnyManagedParkingLot(parkingBoy)) {
            throw new FailedToDoOperationException();
        }

        return managedParkingBoys.stream()
                .filter(parkingBoy::equals)
                .findFirst()
                .orElseThrow(FailedToDoOperationException::new)
                .fetch(parkingTicket);
    }

    private boolean isParkingBoyAssignedToAnyManagedParkingLot(ParkingBoy parkingBoy) {
        return parkingBoy.getParkingLots().stream().noneMatch(parkingLots::contains);
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
