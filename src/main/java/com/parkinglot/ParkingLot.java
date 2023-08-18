package com.parkinglot;

import com.parkinglot.exception.UnrecognizedTicketException;

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
        if (cars.get(parkingTicket) == null) {
            throw new UnrecognizedTicketException();
        }
        return cars.remove(parkingTicket);
    }
}
