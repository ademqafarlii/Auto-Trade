package org.adem.autotrade.controller;

import lombok.RequiredArgsConstructor;
import org.adem.autotrade.model.Announcement;
import org.adem.autotrade.service.AnnouncementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcement")
@RequiredArgsConstructor
public class AnnouncementController {


    private final AnnouncementService announcementService;


    @PostMapping("/add-announcement")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAnnouncement(@RequestBody Announcement announcement) {
        announcementService.addAnnouncement(announcement);
    }

    @PutMapping("/update-announcement-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateAnnouncementByID(@PathVariable Integer id,@RequestBody Announcement announcement) {
        announcementService.updateAnnouncementByID(id, announcement);
    }

    @PutMapping("/partial-update-announcement-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void partialUpdateAnnouncementByID(@PathVariable Integer id,@RequestBody Announcement announcement) {
        announcementService.updateAnnouncementByID(id, announcement);
    }

    @GetMapping("/get-all-announcement")
    @ResponseStatus(HttpStatus.OK)
    public List<Announcement> getAllAnnouncements() {
        return announcementService.getAllAnnouncements();
    }

    @GetMapping("/get-announcement-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Announcement getAnnouncementByID(@PathVariable Integer id) {
        return announcementService.getAnnouncementByID(id);
    }

    @GetMapping("/get-announcement-by-car-id/{carId}")
    @ResponseStatus(HttpStatus.OK)
    public Announcement getAnnouncementByCarID(@PathVariable Integer carId) {
        return announcementService.getAnnouncementByCarID(carId);
    }

    @DeleteMapping("/delete-announcement-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAnnouncementByID(@PathVariable Integer id) {
        announcementService.deleteAnnouncementByID(id);
    }
}
