package com.parkinglot.members;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.exception.UnrecognizedTicketException;
import com.parkinglot.service.ParkingStrategy;

import java.util.List;

public abstract class ParkingBoy {

    private final List<ParkingLot> parkingLots;

    private ParkingStrategy parkingStrategy;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public abstract ParkingTicket park(Car car);

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.isTicketForCarInParkingLot(parkingTicket))
                .findFirst()
                .orElseThrow(UnrecognizedTicketException::new)
                .fetch(parkingTicket);
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public ParkingStrategy getParkingStrategy() {
        return parkingStrategy;
    }
}
