package com.parkinglot.service;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.exception.NoAvailablePositionException;

import java.util.List;

public class StandardParking implements ParkingStrategy {

    @Override
    public ParkingTicket park(Car car, List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(ParkingLot::hasAvailableCapacity)
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }
}
