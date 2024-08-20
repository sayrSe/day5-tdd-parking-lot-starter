package com.parkinglot.members;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.service.StandardParking;

import java.util.List;

public class StandardParkingBoy extends ParkingBoy {

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
        setParkingStrategy(new StandardParking());
    }

    public ParkingTicket park(Car car) {
        return getParkingStrategy().park(car, super.getParkingLots());
    }
}
