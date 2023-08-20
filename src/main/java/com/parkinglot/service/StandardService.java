package com.parkinglot.service;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.List;

public class StandardService implements ServiceStrategy {

    @Override
    public ParkingTicket park(Car car, List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(ParkingLot::hasAvailableCapacity)
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket, List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(parkingLot -> isTicketForCarInParkingLot(parkingTicket, parkingLot))
                .findFirst()
                .orElseThrow(UnrecognizedTicketException::new)
                .fetch(parkingTicket);
    }

    private static boolean isTicketForCarInParkingLot(ParkingTicket parkingTicket, ParkingLot parkingLot) {
        return parkingLot.getTicketCarMap().containsKey(parkingTicket);
    }
}
