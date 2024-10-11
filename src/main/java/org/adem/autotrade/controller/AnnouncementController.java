package org.adem.autotrade.controller;

import lombok.RequiredArgsConstructor;
import org.adem.autotrade.dto.request.AnnouncementRequestDto;
import org.adem.autotrade.dto.response.AnnouncementResponseDto;
import org.adem.autotrade.service.AnnouncementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/announcement")
@RequiredArgsConstructor
public class AnnouncementController {


    private final AnnouncementService announcementService;


    @PostMapping("/add-announcement")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAnnouncement(@RequestBody AnnouncementRequestDto announcement) {
        announcementService.addAnnouncement(announcement);
    }

    @PutMapping("/update-announcement-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateAnnouncementByID(@PathVariable Integer id, @RequestBody AnnouncementRequestDto announcement) {
        announcementService.updateAnnouncementByID(id, announcement);
    }

    @PutMapping("/partial-update-announcement-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void partialUpdateAnnouncementByID(@PathVariable Integer id, @RequestBody AnnouncementRequestDto announcement) {
        announcementService.updateAnnouncementByID(id, announcement);
    }

    @GetMapping("/get-all-announcement")
    @ResponseStatus(HttpStatus.OK)
    public Page<AnnouncementResponseDto> getAllAnnouncements(@PageableDefault(value = 12) Pageable pageable) {
        return announcementService.getAllAnnouncements(pageable);
    }

    @GetMapping("/get-announcement-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnnouncementResponseDto getAnnouncementByID(@PathVariable Integer id) {
        return announcementService.getAnnouncementByID(id);
    }

    @GetMapping("/get-announcement-by-car-id/{carId}")
    @ResponseStatus(HttpStatus.OK)
    public AnnouncementResponseDto getAnnouncementByCarID(@PathVariable Integer carId) {
        return announcementService.getAnnouncementByCarID(carId);
    }

    @DeleteMapping("/delete-announcement-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAnnouncementByID(@PathVariable Integer id) {
        announcementService.deleteAnnouncementByID(id);
    }

    @GetMapping("/find-by-spec")
    @ResponseStatus(HttpStatus.OK)
    public Page<AnnouncementResponseDto> findBySpecification(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false) LocalDateTime createDate,
                                                  @PageableDefault(value = 12) Pageable pageable) {
        return announcementService.findBySpecification(name, createDate, pageable);
    }
}
