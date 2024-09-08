package org.adem.autotrade.repository;

import org.adem.autotrade.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {
    Optional<Announcement> findByCar_Id(Integer carId);
}
