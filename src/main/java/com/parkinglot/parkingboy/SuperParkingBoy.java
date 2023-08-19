package com.parkinglot.parkingboy;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.exception.NoAvailablePositionException;

import java.util.Comparator;
import java.util.List;

public class SuperParkingBoy extends StandardParkingBoy {

    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingTicket park(Car car) {
        return super.getParkingLots().stream()
                .filter(ParkingLot::hasAvailableCapacity)
                .max(Comparator.comparing(ParkingLot::getAvailablePositionRate))
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }
}
