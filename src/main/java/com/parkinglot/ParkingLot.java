package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    public static final int DEFAULT_CAPACITY = 10;
    private final Map<ParkingTicket, Car> cars = new HashMap<>();
    private final int capacity;

    public ParkingLot() {
        capacity = DEFAULT_CAPACITY;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car) {
        if (isFull()) {
            return null;
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        cars.put(parkingTicket, car);
        return parkingTicket;
    }

    private boolean isFull() {
        return cars.size() == capacity;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car car = cars.get(parkingTicket);
        cars.remove(parkingTicket);
        return car;
    }
}
