package com.parkinglot.parkingboy;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.service.StandardService;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager {

    private final List<StandardParkingBoy> management = new ArrayList<>();
    private final List<ParkingLot> parkingLots;

    public ParkingLotServiceManager(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        return new StandardService().park(car, parkingLots);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return new StandardService().fetch(parkingTicket, parkingLots);
    }

    public List<StandardParkingBoy> getManagement() {
        return management;
    }

    public void addToManagement(StandardParkingBoy standardParkingBoy) {
        standardParkingBoy.getParkingLots().addAll(parkingLots);
        management.add(standardParkingBoy);
    }

    public ParkingTicket parkWithParkingBoy(StandardParkingBoy standardParkingBoy, Car car) {
        return management.stream()
                .filter(standardParkingBoy::equals)
                .findFirst()
                .orElseThrow()
                .park(car);
    }

    public Car fetchWithParkingBoy(StandardParkingBoy standardParkingBoy, ParkingTicket parkingTicket) {
        return management.stream()
                .filter(standardParkingBoy::equals)
                .findFirst()
                .orElseThrow()
                .fetch(parkingTicket);
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
