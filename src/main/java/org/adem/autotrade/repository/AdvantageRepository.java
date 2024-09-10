package org.adem.autotrade.repository;

import org.adem.autotrade.model.Advantage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvantageRepository extends JpaRepository<Advantage, Integer> {


    @Query(value = "select * from car_advantage c where c.car_id=:carId and c.advantage_id=:advantageId", nativeQuery = true)
    List<Advantage> findByCarIdAndAdvantageId(@Param("carId") Integer carId, @Param("advantageId") Integer advantageId);

    @Query(value = "select * from car_advantage c where c.car_id=:id",nativeQuery = true)
    List<Advantage> findByCarID(@Param("id") Integer id);

    @Modifying
    @Query(value = "delete from car_advantage where car_id=:carId and advantage_id=:advantageId", nativeQuery = true)
    void deleteByAdvantageIdAndCarID(@Param("carId") Integer carId, @Param("advantageId") Integer advantageId);

}
