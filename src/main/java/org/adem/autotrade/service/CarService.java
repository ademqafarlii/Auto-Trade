package org.adem.autotrade.service;

import org.adem.autotrade.model.Car;

import java.util.List;

public interface CarService {

    void addCar(Car car);

    void updateCarByID(Integer id, Car car);

    List<Car> getAllCars();

    Car getCarById(Integer id);

    void deleteCarByID(Integer id);
}
