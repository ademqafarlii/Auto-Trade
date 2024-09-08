package org.adem.autotrade.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.adem.autotrade.exception.CarNotFoundException;
import org.adem.autotrade.model.CarDetail;
import org.adem.autotrade.repository.CarDetailRepository;
import org.adem.autotrade.service.CarDetailService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarDetailServiceImpl implements CarDetailService {

    private final CarDetailRepository carDetailRepository;

    @Override
    public void addCarDetails(CarDetail carDetail) {
        carDetailRepository.save(carDetail);

    }

    @Override
    public void updateCarDetailsByCarId(Integer id, CarDetail carDetail) {
        Optional<CarDetail> car = carDetailRepository.getCarDetailByCar_Id(id);
        if (car.isEmpty()) {
            throw new CarNotFoundException("Car not found");
        }
        car.get().setMileage(carDetail.getMileage());
        car.get().setTransmission(carDetail.getTransmission());
        car.get().setIsNew(carDetail.getIsNew());
        car.get().setVanType(carDetail.getVanType());
        car.get().setColor(carDetail.getColor());
        car.get().setSituation(carDetail.getSituation());
        car.get().setOwnerCount(carDetail.getOwnerCount());
        car.get().setVinCode(carDetail.getVinCode());
        car.get().setDescription(carDetail.getDescription());
        car.get().setFuelType(carDetail.getFuelType());
        car.get().setEnginePower(carDetail.getEnginePower());
        carDetailRepository.save(car.get());

    }

    @Override
    public List<CarDetail> getAllCarDetails() {
        return carDetailRepository.findAll();
    }

    @Override
    public CarDetail getCarDetailsByCarId(Integer id) {
        return carDetailRepository.getCarDetailByCar_Id(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found"));
    }

    @Override
    public void deleteCarDetailsByCarId(Integer id) {
        Optional<CarDetail> carDetail = carDetailRepository.getCarDetailByCar_Id(id);
        if (carDetail.isEmpty()) {
            throw new CarNotFoundException("Car not found");
        }
        carDetailRepository.deleteByCar_Id(id);
    }
}
