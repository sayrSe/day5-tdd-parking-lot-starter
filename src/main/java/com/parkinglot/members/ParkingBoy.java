package com.parkinglot.members;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.List;

public class ParkingBoy {

    private final List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

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
}
