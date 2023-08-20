package com.parkinglot.service;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.exception.NoAvailablePositionException;

import java.util.Comparator;
import java.util.List;

public class SuperService implements ServiceStrategy {

    @Override
    public ParkingTicket park(Car car, List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(ParkingLot::hasAvailableCapacity)
                .max(Comparator.comparing(ParkingLot::getAvailablePositionRate))
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket, List<ParkingLot> parkingLots) {
        return null;
    }
}