package com.parkinglot.members;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.service.SuperParking;

import java.util.List;

public class SuperParkingBoy extends ParkingBoy {

    private final SuperParking superParking = new SuperParking();

    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingTicket park(Car car) {
        return superParking.park(car, super.getParkingLots());
    }
}
