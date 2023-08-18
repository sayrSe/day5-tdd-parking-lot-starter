package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private final Map<ParkingTicket, Car> cars = new HashMap<>();
    private final int capacity;

    public ParkingLot() {
        capacity = 10;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car) {
        if (cars.size() == capacity) {
            return null;
        }
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
