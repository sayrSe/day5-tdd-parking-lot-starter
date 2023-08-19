package com.parkinglot.parkingboy;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;

import java.util.List;

public class ParkingBoyTestDataFactory {

    public static List<ParkingLot> buildTwoEmptyParkingLots() {
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        return List.of(firstParkingLot, secondParkingLot);
    }

    public static List<ParkingLot> buildTwoFullParkingLots() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        Car firstParkedCar = new Car();
        firstParkingLot.park(firstParkedCar);

        ParkingLot secondParkingLot = new ParkingLot(1);
        Car secondParkedCar = new Car();
        secondParkingLot.park(secondParkedCar);

        return List.of(firstParkingLot, secondParkingLot);
    }
}