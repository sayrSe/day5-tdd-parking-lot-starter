package com.parkinglot.members;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;

import java.util.List;

public class ParkingBoyTestDataFactory {

    public static List<ParkingLot> buildTwoEmptyParkingLots() {
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        return List.of(firstParkingLot, secondParkingLot);
    }

    public static ParkingLot buildFullParkingLot() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        parkingLot.park(car);
        return parkingLot;
    }

    public static List<ParkingLot> buildTwoFullParkingLots() {
        return List.of(buildFullParkingLot(), buildFullParkingLot());
    }

    public static ParkingLot buildParkingLotWithThreeCapacityAndOneParkedCar() {
        ParkingLot parkingLot = new ParkingLot(3);
        Car car = new Car();
        parkingLot.park(car);
        return parkingLot;
    }

    public static ParkingLot buildParkingLotWithFiveCapacityAndTwoParkedCar() {
        ParkingLot parkingLot = new ParkingLot(5);
        Car firstParkedCar = new Car();
        Car secondthirdParkedCar = new Car();
        parkingLot.park(firstParkedCar);
        parkingLot.park(secondthirdParkedCar);
        return parkingLot;
    }
}
