package com.parkinglot.members;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.service.StandardService;

import java.util.List;

public class StandardParkingBoy {

    private final List<ParkingLot> parkingLots;

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        return new StandardService().park(car, parkingLots);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return new StandardService().fetch(parkingTicket, parkingLots);
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
