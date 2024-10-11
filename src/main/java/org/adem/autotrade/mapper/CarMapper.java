package org.adem.autotrade.mapper;

import org.adem.autotrade.dto.request.CarRequestDto;
import org.adem.autotrade.dto.response.CarResponseDto;
import org.adem.autotrade.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarMapper {

    Car toCarEntity(CarRequestDto carRequestDto);

    CarResponseDto toCarResponse(Car car);

    CarRequestDto fromResponseToRequest(CarResponseDto carResponseDto);
}
