package com.parkinglot.members;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.service.SuperParking;

import java.util.List;

public class SuperParkingBoy extends ParkingBoy {

    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
        setParkingStrategy(new SuperParking());
    }

    public ParkingTicket park(Car car) {
        return getParkingStrategy().park(car, super.getParkingLots());
    }
}
