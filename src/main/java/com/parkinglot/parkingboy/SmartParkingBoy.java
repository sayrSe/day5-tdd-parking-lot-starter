package com.parkinglot.parkingboy;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.exception.NoAvailablePositionException;

import java.util.Comparator;
import java.util.List;

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
