package org.adem.autotrade.service;

import org.adem.autotrade.dto.request.CarRequestDto;
import org.adem.autotrade.dto.response.CarResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CarService {

    void addCar(CarRequestDto carRequestDto);

    void updateCarByID(Integer id, CarRequestDto carRequestDto);

    Page<CarResponseDto> getAllCars(Pageable pageable);

    CarResponseDto getCarById(Integer id);

    Page<CarResponseDto> getCarsByModel(String model, Pageable pageable);

    void deleteCarByID(Integer id);

    Page<CarResponseDto> findBySpecification(String brand,
                                             String model,
                                             Integer year,
                                             Pageable pageable);
}
