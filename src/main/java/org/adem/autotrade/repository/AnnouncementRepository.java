package org.adem.autotrade.repository;

import org.adem.autotrade.model.Announcement;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AnnouncementRepository extends JpaRepository<Announcement,Integer>, JpaSpecificationExecutor<Announcement> {

    @EntityGraph(value = "announcement-graph")
    Optional<Announcement> findByCar_Id(Integer carId);
}
