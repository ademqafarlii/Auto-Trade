package org.adem.autotrade.mapper;

import org.adem.autotrade.dto.request.CarDetailRequestDto;
import org.adem.autotrade.dto.response.CarDetailResponseDto;
import org.adem.autotrade.model.CarDetail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarDetailMapper {

    CarDetail toCarDetailEntity(CarDetailRequestDto carRequestDto);

    CarDetailResponseDto toCarDetailResponse(CarDetail carDetail);

    CarDetailRequestDto fromResponseToRequest(CarDetailResponseDto carDetailResponseDto);
}
