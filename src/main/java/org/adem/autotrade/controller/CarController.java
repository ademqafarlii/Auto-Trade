package org.adem.autotrade.controller;

import lombok.RequiredArgsConstructor;
import org.adem.autotrade.model.Car;
import org.adem.autotrade.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController{
    private final CarService carService;

    @PostMapping("/add-car")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCar(@RequestBody Car car) {
        carService.addCar(car);
    }

    @PutMapping("/update-car-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCarByID(@PathVariable Integer id, @RequestBody Car car) {
        carService.updateCarByID(id, car);
    }

    @PatchMapping("/partial-update-car-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void partialUpdateCarByID(@PathVariable Integer id, @RequestBody Car car) {
        carService.updateCarByID(id, car);
    }

    @GetMapping("/get-all-cars")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/get-car-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Car getCarById(@PathVariable Integer id) {
        return carService.getCarById(id);
    }

    @DeleteMapping("/delete-car-by-id/{id}")
    public void deleteCarByID(@PathVariable Integer id) {
        carService.deleteCarByID(id);
    }
}
