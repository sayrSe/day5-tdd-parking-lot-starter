package com.parkinglot.parkingboy;

import com.parkinglot.ParkingLot;

import java.util.List;

public class ParkingBoyTestDataFactory {

    public static List<ParkingLot> buildTwoEmptyParkingLots() {
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        return List.of(firstParkingLot, secondParkingLot);
    }
}
