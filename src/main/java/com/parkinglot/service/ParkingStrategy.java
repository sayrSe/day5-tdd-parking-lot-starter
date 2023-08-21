package com.parkinglot.service;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;

import java.util.List;

public interface ParkingStrategy {

    ParkingTicket park(Car car, List<ParkingLot> parkingLots);
}