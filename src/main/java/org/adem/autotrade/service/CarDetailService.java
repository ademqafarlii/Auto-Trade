package org.adem.autotrade.service;

import org.adem.autotrade.dto.request.CarDetailRequestDto;
import org.adem.autotrade.dto.response.CarDetailResponseDto;
import org.adem.autotrade.enums.Transmission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CarDetailService {

    void addCarDetails(CarDetailRequestDto carDetailRequestDto);

    void updateCarDetailsByCarId(Integer id, CarDetailRequestDto carDetailRequestDto);

    Page<CarDetailResponseDto> getAllCarDetails(Pageable pageable);

    CarDetailResponseDto getCarDetailsByCarId(Integer id);

    void deleteCarDetailsByCarId(Integer id);

    Page<CarDetailResponseDto> findBySpecification(Long mileage,
                                                   Transmission transmission,
                                                   Boolean isNew,
                                                   String vanType,
                                                   String color,
                                                   Pageable pageable);
}
