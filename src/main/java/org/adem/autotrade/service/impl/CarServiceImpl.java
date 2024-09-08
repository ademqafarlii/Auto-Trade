package org.adem.autotrade.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.adem.autotrade.exception.CarNotFoundException;
import org.adem.autotrade.model.Car;
import org.adem.autotrade.repository.CarRepository;
import org.adem.autotrade.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public void addCar(Car car) {
        carRepository.save(car);
        log.info("Car added with id: {}", car.getId());
    }

    @Override
    public void updateCarByID(Integer id, Car car) {
        Optional<Car> existingCar = carRepository.findById(id);
        if (existingCar.isEmpty()) {
            throw new CarNotFoundException("Car not found");
        }
        existingCar.get().setCarDetail(car.getCarDetail());
        existingCar.get().setAdvantage(car.getAdvantage());
        existingCar.get().setBrand(car.getBrand());
        existingCar.get().setPrice(car.getPrice());
        existingCar.get().setModel(car.getModel());
        existingCar.get().setYear(car.getYear());

        carRepository.save(existingCar.get());
        log.info("Car updated with id:{}", car.getId());
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> carList = carRepository.findAll();
        if (carList.isEmpty()) {
            throw new CarNotFoundException("Car not found");
        }
        return carList;
    }

    @Override
    public Car getCarById(Integer id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found"));
    }

    @Override
    public void deleteCarByID(Integer id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isEmpty()) {
            throw new CarNotFoundException("Car not found");
        }
        carRepository.deleteById(id);
        log.info("Car deleted with id: {} ", car.get().getId());
    }
}
