package org.adem.autotrade.service;

import org.adem.autotrade.model.CarDetail;

import java.util.List;

public interface CarDetailService {

    void addCarDetails(CarDetail carDetail);

    void updateCarDetailsByCarId(Integer id, CarDetail carDetail);

    List<CarDetail> getAllCarDetails();

    CarDetail getCarDetailsByCarId(Integer id);

    void deleteCarDetailsByCarId(Integer id);
}
