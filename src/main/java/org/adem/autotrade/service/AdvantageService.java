package org.adem.autotrade.service;

import org.adem.autotrade.model.Advantage;

import java.util.List;

public interface AdvantageService {

    void addAdvantage(Advantage advantage);

    void updateAdvantagesByCarID(Integer carID,Advantage advantage);

    List<Advantage> getAllAdvantagesByCarId(Integer id);

    void deleteAdvantageFromCar(Integer carId, Integer advantageId);
}
