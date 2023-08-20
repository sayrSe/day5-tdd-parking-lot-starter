package com.parkinglot.service;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;

import java.util.List;

public interface ServiceStrategy {

    ParkingTicket park(Car car, List<ParkingLot> parkingLots);

    Car fetch(ParkingTicket parkingTicket, List<ParkingLot> parkingLots);
}