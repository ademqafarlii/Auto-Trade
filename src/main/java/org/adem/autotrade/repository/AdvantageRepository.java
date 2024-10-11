package org.adem.autotrade.repository;

import org.adem.autotrade.model.Advantage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvantageRepository extends JpaRepository<Advantage, Integer>, JpaSpecificationExecutor<Advantage> {


    @Query("select a from Car c join c.advantage a where c.id = :carId and a.id = :advantageId")
    List<Advantage> findByCarIdAndAdvantageId(@Param("carId") Integer carId, @Param("advantageId") Integer advantageId);


    @Query("select a from Car c join c.advantage a where c.id = :id")
    Page<Advantage> findByCarID(@Param("id") Integer id, Pageable pageable);


    @Query("select a from Car c join c.advantage a where c.id = :id")
    List<Advantage> findByCarIDList(@Param("id") Integer id);


    @Modifying
    @Query("delete from Car c where c.id = :carId and exists (select a from c.advantage a where a.id = :advantageId)")
    void deleteByAdvantageIdAndCarID(@Param("carId") Integer carId, @Param("advantageId") Integer advantageId);



}
