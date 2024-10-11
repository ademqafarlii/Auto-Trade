package org.adem.autotrade.service;

import org.adem.autotrade.dto.request.AdvantageRequestDto;
import org.adem.autotrade.dto.response.AdvantageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AdvantageService {

    void addAdvantage(AdvantageRequestDto advantageRequestDto);

    void updateAdvantagesByCarID(Integer carID, AdvantageRequestDto advantageRequestDto);

    Page<AdvantageResponseDto> getAllAdvantagesByCarId(Integer id, Pageable pageable);

    void deleteAdvantageFromCar(Integer carId, Integer advantageId);

    Page<AdvantageResponseDto> findBySpecification(Boolean abs,
                                                   Boolean luke,
                                                   Boolean rainSensor,
                                                   Boolean centralLock,
                                                   Boolean parkingSensor,
                                                   Boolean airConditioner,
                                                   Boolean heatedSeats,
                                                   Pageable pageable);
}
