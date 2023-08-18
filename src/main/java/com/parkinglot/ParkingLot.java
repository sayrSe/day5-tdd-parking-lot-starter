package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        cars.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car car = cars.get(parkingTicket);
        cars.remove(parkingTicket);
        return car;
    }
}
