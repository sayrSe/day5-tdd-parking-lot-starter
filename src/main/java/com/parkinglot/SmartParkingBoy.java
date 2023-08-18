package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartParkingBoy extends StandardParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingTicket park(Car car) {
        return super.getParkingLots().stream()
                .max(Comparator.comparing(ParkingLot::getAvailableCapacity))
                .filter(ParkingLot::hasAvailableCapacity)
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }
}
