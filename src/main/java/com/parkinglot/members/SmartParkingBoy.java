package com.parkinglot.members;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.service.SmartParking;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    private final SmartParking smartParking = new SmartParking();

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingTicket park(Car car) {
        return smartParking.park(car, super.getParkingLots());
    }
}
