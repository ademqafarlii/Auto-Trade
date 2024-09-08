package org.adem.autotrade.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.adem.autotrade.exception.AnnouncementNotFoundException;
import org.adem.autotrade.exception.CarNotFoundException;
import org.adem.autotrade.model.Announcement;
import org.adem.autotrade.repository.AnnouncementRepository;
import org.adem.autotrade.service.AnnouncementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Override
    public void addAnnouncement(Announcement announcement) {
        announcementRepository.save(announcement);
        log.info("Announcement added with id:{}", announcement.getId());
    }

    @Override
    public void updateAnnouncementByID(Integer id, Announcement announcement) {
        Optional<Announcement> existingAnnouncement = announcementRepository.findById(id);
        if (existingAnnouncement.isEmpty()) {
            throw new AnnouncementNotFoundException("Announcement not found");
        }
        existingAnnouncement.get().setAnnouncementName(announcement.getAnnouncementName());
        existingAnnouncement.get().setCreateDate(announcement.getCreateDate());
        existingAnnouncement.get().setUpdateDate(announcement.getUpdateDate());
        existingAnnouncement.get().setExpireDate(announcement.getExpireDate());
        existingAnnouncement.get().setViewCount(announcement.getViewCount());
        existingAnnouncement.get().setDescription(announcement.getDescription());
        existingAnnouncement.get().setStatus(announcement.getStatus());
        announcementRepository.save(existingAnnouncement.get());

        log.info("Announcement updated with id:{}", id);

    }

    @Override
    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    @Override
    public Announcement getAnnouncementByID(Integer id) {
        return announcementRepository.findById(id)
                .orElseThrow(() -> new AnnouncementNotFoundException("Announcement not found"));
    }

    @Override
    public Announcement getAnnouncementByCarID(Integer carId) {
        return announcementRepository.findByCar_Id(carId)
                .orElseThrow(() -> new CarNotFoundException("Announcement not found. There is no car with this id"));
    }

    @Override
    public void deleteAnnouncementByID(Integer id) {
        if (announcementRepository.findById(id).isEmpty()) {
            throw new AnnouncementNotFoundException("Announcement not found");
        }
        announcementRepository.deleteById(id);

    }
}
