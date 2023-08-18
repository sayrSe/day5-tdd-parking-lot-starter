package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;

import java.util.List;

public class StandardParkingBoy {

    private final List<ParkingLot> parkingLots;

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        return parkingLots.stream()
                .filter(ParkingLot::hasAvailableCapacity)
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLots.stream()
                .filter(parkingLot -> isTicketForCarInParkingLot(parkingTicket, parkingLot))
                .findFirst()
                .orElseThrow()
                .fetch(parkingTicket);
    }

    private static boolean isTicketForCarInParkingLot(ParkingTicket parkingTicket, ParkingLot parkingLot) {
        return parkingLot.getTicketCarMap().containsKey(parkingTicket);
    }
}
