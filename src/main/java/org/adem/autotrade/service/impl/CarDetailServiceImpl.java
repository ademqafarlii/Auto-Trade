package org.adem.autotrade.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.adem.autotrade.dto.request.CarDetailRequestDto;
import org.adem.autotrade.dto.response.CarDetailResponseDto;
import org.adem.autotrade.enums.Transmission;
import org.adem.autotrade.exception.CarNotFoundException;
import org.adem.autotrade.mapper.CarDetailMapper;
import org.adem.autotrade.model.CarDetail;
import org.adem.autotrade.repository.CarDetailRepository;
import org.adem.autotrade.service.CarDetailService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarDetailServiceImpl implements CarDetailService {

    private final CarDetailRepository carDetailRepository;
    private final CarDetailMapper carDetailMapper;

    @Override
    public void addCarDetails(CarDetailRequestDto carDetail) {
        carDetailRepository.save(carDetailMapper.toCarDetailEntity(carDetail));

    }

    @Override
    public void updateCarDetailsByCarId(Integer id, CarDetailRequestDto carDetail) {
        Optional<CarDetail> car = carDetailRepository.getCarDetailByCar_Id(id);
        if (car.isEmpty()) {
            throw new CarNotFoundException("Car not found");
        }
        car.get().setMileage(carDetail.getMileage());
        car.get().setTransmission(carDetail.getTransmission());
        car.get().setIsNew(carDetail.getIsNew());
        car.get().setVanType(carDetail.getVanType());
        car.get().setColor(carDetail.getColor());
        car.get().setSituation(carDetail.getSituation());
        car.get().setOwnerCount(carDetail.getOwnerCount());
        car.get().setVinCode(carDetail.getVinCode());
        car.get().setDescription(carDetail.getDescription());
        car.get().setFuelType(carDetail.getFuelType());
        car.get().setEnginePower(carDetail.getEnginePower());
        carDetailRepository.save(car.get());

    }

    @Override
    public Page<CarDetailResponseDto> getAllCarDetails(Pageable pageable) {
        return carDetailRepository.findAll(pageable).map(carDetailMapper::toCarDetailResponse);
    }

    @Override
    public CarDetailResponseDto getCarDetailsByCarId(Integer id) {
        return carDetailRepository.getCarDetailByCar_Id(id)
                .stream()
                .map(carDetailMapper::toCarDetailResponse)
                .findFirst()
                .orElseThrow(() -> new CarNotFoundException("Car not found"));
    }

    @Override
    public void deleteCarDetailsByCarId(Integer id) {
        Optional<CarDetail> carDetail = carDetailRepository.getCarDetailByCar_Id(id);
        if (carDetail.isEmpty()) {
            throw new CarNotFoundException("Car not found");
        }
        carDetailRepository.deleteByCar_Id(id);
    }

    @Override
    public Page<CarDetailResponseDto> findBySpecification(Long mileage,
                                                          Transmission transmission,
                                                          Boolean isNew,
                                                          String vanType,
                                                          String color,
                                                          Pageable pageable) {
        Specification<CarDetail> specification = null;

        specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (mileage != null) {
                predicateList.add(criteriaBuilder.equal(root.get("mileage"), mileage));
            }
            if (transmission != null) {
                predicateList.add(criteriaBuilder.equal(root.get("transmission"), transmission));
            }
            if (isNew != null) {
                predicateList.add(criteriaBuilder.equal(root.get("isNew"), isNew));
            }
            if (vanType != null) {
                predicateList.add(criteriaBuilder.equal(root.get("vanType"), vanType));
            }
            if (color != null) {
                predicateList.add(criteriaBuilder.equal(root.get("color"), color));
            }
            Objects.requireNonNull(query).where(
                    criteriaBuilder.and(predicateList.toArray(predicateList.toArray(new Predicate[0])))
            );

            return query.getRestriction();
        };
        return carDetailRepository.findAll(specification, pageable).map(carDetailMapper::toCarDetailResponse);
    }
}
