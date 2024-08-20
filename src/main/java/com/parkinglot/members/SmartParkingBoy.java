package com.parkinglot.members;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.service.SmartParking;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
        setParkingStrategy(new SmartParking());
    }

    public ParkingTicket park(Car car) {
        return getParkingStrategy().park(car, super.getParkingLots());
    }
}
