package org.adem.autotrade.mapper;

import org.adem.autotrade.dto.request.AnnouncementRequestDto;
import org.adem.autotrade.dto.response.AnnouncementResponseDto;
import org.adem.autotrade.model.Announcement;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnnouncementMapper {

    Announcement toAnnouncementEntity(AnnouncementRequestDto announcementRequestDto);

    AnnouncementRequestDto fromResponseToRequest(AnnouncementResponseDto announcementResponseDto);

    AnnouncementResponseDto toAnnouncementResponse(Announcement announcement);
}
