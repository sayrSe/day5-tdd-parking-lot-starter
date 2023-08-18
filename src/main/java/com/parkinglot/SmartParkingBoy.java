package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SmartParkingBoy extends StandardParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingTicket park(Car car) {
        return getParkingLotWithMoreSpace(super.getParkingLots()).stream()
                .filter(ParkingLot::hasAvailableCapacity)
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }

    private static List<ParkingLot> getParkingLotWithMoreSpace(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .max(Comparator.comparing(ParkingLot::getAvailableCapacity))
                .stream()
                .collect(Collectors.toList());
    }
}
