package org.adem.autotrade.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.adem.autotrade.dto.request.AdvantageRequestDto;
import org.adem.autotrade.dto.response.AdvantageResponseDto;
import org.adem.autotrade.exception.AdvantageNotFoundException;
import org.adem.autotrade.mapper.AdvantageMapper;
import org.adem.autotrade.model.Advantage;
import org.adem.autotrade.repository.AdvantageRepository;
import org.adem.autotrade.service.AdvantageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdvantageServiceImpl implements AdvantageService {

    private final AdvantageMapper advantageMapper;

    private final AdvantageRepository advantageRepository;

    @Override
    public void addAdvantage(AdvantageRequestDto advantage) {
        advantageRepository.save(advantageMapper.toAdvantageEntity(advantage));
    }

    @Override
    public void updateAdvantagesByCarID(Integer carID, AdvantageRequestDto advantage) {
        List<Advantage> advantages = advantageRepository.findByCarIDList(carID);

        if (advantages.isEmpty()) {
            throw new AdvantageNotFoundException("Advantage not found");
        }
        advantages.forEach(newAdvantage -> {
            newAdvantage.setAbs(advantage.getAbs());
            newAdvantage.setLuke(advantage.getLuke());
            newAdvantage.setAirConditioner(advantage.getAirConditioner());
            newAdvantage.setCentralLock(advantage.getCentralLock());
            newAdvantage.setParkingSensors(advantage.getParkingSensors());
            newAdvantage.setRainSensor(advantage.getRainSensor());
            newAdvantage.setHeatedSeats(advantage.getHeatedSeats());

            advantageRepository.save(newAdvantage);
        });
    }

    @Override
    public Page<AdvantageResponseDto> getAllAdvantagesByCarId(Integer id, Pageable pageable) {
        Page<Advantage> advantages = advantageRepository.findByCarID(id, pageable);
        if (advantages.isEmpty()) {
            throw new AdvantageNotFoundException("Advantage not found");
        }
        return advantages.map(advantageMapper::toAdvantageResponse);
    }

    @Override
    public void deleteAdvantageFromCar(Integer carId, Integer advantageId) {
        List<Advantage> advantages = advantageRepository.findByCarIdAndAdvantageId(carId, advantageId);
        if (advantages.isEmpty()) {
            throw new AdvantageNotFoundException("Advantage not found");
        }
        advantageRepository.deleteByAdvantageIdAndCarID(carId, advantageId);
    }


    @Override
    public Page<AdvantageResponseDto> findBySpecification(Boolean abs,
                                                          Boolean luke,
                                                          Boolean rainSensor,
                                                          Boolean centralLock,
                                                          Boolean parkingSensor,
                                                          Boolean airConditioner,
                                                          Boolean heatedSeats,
                                                          Pageable pageable) {
        Specification<Advantage> specification;

        specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (abs != null) {
                predicateList.add(criteriaBuilder.equal(root.get("abs"), abs));
            }
            if (luke != null) {
                predicateList.add(criteriaBuilder.equal(root.get("luke"), luke));
            }
            if (rainSensor != null) {
                predicateList.add(criteriaBuilder.equal(root.get("rainSensor"), rainSensor));
            }
            if (centralLock != null) {
                predicateList.add(criteriaBuilder.equal(root.get("centralLock"), centralLock));
            }
            if (parkingSensor != null) {
                predicateList.add(criteriaBuilder.equal(root.get("parkingSensor"), parkingSensor));
            }
            if (airConditioner != null) {
                predicateList.add(criteriaBuilder.equal(root.get("airConditioner"), airConditioner));
            }
            if (heatedSeats != null) {
                predicateList.add(criteriaBuilder.equal(root.get("heatedSeats"), heatedSeats));
            }

            Objects.requireNonNull(query).where(
                    criteriaBuilder.and(predicateList.toArray(predicateList.toArray(new Predicate[0])))
            );

            return query.getRestriction();
        };
        return advantageRepository.findAll(specification, pageable).map(advantageMapper::toAdvantageResponse);
    }
}
