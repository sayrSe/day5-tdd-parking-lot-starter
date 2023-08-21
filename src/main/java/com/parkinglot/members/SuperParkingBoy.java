package com.parkinglot.members;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.service.SuperParking;

import java.util.List;

public class SuperParkingBoy extends ParkingBoy {

    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingTicket park(Car car) {
        return new SuperParking().park(car, super.getParkingLots());
    }
}
