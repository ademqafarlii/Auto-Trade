package org.adem.autotrade.repository;

import org.adem.autotrade.model.CarDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CarDetailRepository extends JpaRepository<CarDetail, Integer>, JpaSpecificationExecutor<CarDetail> {
    Optional<CarDetail> getCarDetailByCar_Id(Integer id);

    void deleteByCar_Id(Integer id);
}
