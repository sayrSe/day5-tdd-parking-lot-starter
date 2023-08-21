package com.parkinglot.members;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.service.StandardParking;

import java.util.List;

public class StandardParkingBoy extends ParkingBoy {

    private final StandardParking standardService = new StandardParking();

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingTicket park(Car car) {
        return standardService.park(car, super.getParkingLots());
    }
}
