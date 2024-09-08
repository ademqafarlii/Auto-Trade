package org.adem.autotrade.service;

import org.adem.autotrade.model.Announcement;

import java.util.List;

public interface AnnouncementService {

    void addAnnouncement(Announcement announcement);

    void updateAnnouncementByID(Integer id,Announcement announcement);

    List<Announcement> getAllAnnouncements();

    Announcement getAnnouncementByID(Integer id);

    Announcement getAnnouncementByCarID(Integer carId);

    void deleteAnnouncementByID(Integer id);
}
