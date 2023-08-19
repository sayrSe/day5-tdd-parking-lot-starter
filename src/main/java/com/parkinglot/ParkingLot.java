package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    public static final int DEFAULT_CAPACITY = 10;
    private final Map<ParkingTicket, Car> ticketCarMap = new HashMap<>();
    private final int capacity;

    public ParkingLot() {
        capacity = DEFAULT_CAPACITY;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car) {
        if (isFull()) {
            throw new NoAvailablePositionException();
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        ticketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    private boolean isFull() {
        return ticketCarMap.size() == capacity;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (ticketCarMap.get(parkingTicket) == null) {
            throw new UnrecognizedTicketException();
        }
        return ticketCarMap.remove(parkingTicket);
    }

    public int getAvailableCapacity() {
        return capacity - ticketCarMap.size();
    }


    public boolean hasAvailableCapacity() {
        return !isFull();
    }

    public Map<ParkingTicket, Car> getTicketCarMap() {
        return ticketCarMap;
    }

    public float getAvailablePositionRate() {
        return (float) getAvailableCapacity() / capacity;
    }
}
