package org.adem.autotrade.mapper;

import org.adem.autotrade.dto.request.AdvantageRequestDto;
import org.adem.autotrade.dto.response.AdvantageResponseDto;
import org.adem.autotrade.model.Advantage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdvantageMapper {
    Advantage toAdvantageEntity(AdvantageRequestDto advantageRequestDto);

    AdvantageResponseDto toAdvantageResponse(Advantage advantage);

    List<Advantage> dtoListToEntity(List<AdvantageResponseDto> advantageResponseDto);

    AdvantageRequestDto fromResponseToRequest(AdvantageResponseDto advantageResponseDto);
}
