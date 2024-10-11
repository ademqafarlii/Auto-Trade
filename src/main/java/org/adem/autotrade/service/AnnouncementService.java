package org.adem.autotrade.service;

import org.adem.autotrade.dto.request.AnnouncementRequestDto;
import org.adem.autotrade.dto.response.AnnouncementResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface AnnouncementService {

    void addAnnouncement(AnnouncementRequestDto announcementRequestDto);

    void updateAnnouncementByID(Integer id,AnnouncementRequestDto announcementRequestDto);

    Page<AnnouncementResponseDto> getAllAnnouncements(Pageable pageable);

    AnnouncementResponseDto getAnnouncementByID(Integer id);

    AnnouncementResponseDto getAnnouncementByCarID(Integer carId);

    void deleteAnnouncementByID(Integer id);

    Page<AnnouncementResponseDto> findBySpecification(String name, LocalDateTime createDate,Pageable pageable);
}
