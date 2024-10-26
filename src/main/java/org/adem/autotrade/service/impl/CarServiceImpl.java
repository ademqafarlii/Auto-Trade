package org.adem.autotrade.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.adem.autotrade.dto.request.CarRequestDto;
import org.adem.autotrade.dto.response.CarResponseDto;
import org.adem.autotrade.exception.CarNotFoundException;
import org.adem.autotrade.mapper.AdvantageMapper;
import org.adem.autotrade.mapper.AnnouncementMapper;
import org.adem.autotrade.mapper.CarDetailMapper;
import org.adem.autotrade.mapper.CarMapper;

import org.adem.autotrade.model.Car;
import org.adem.autotrade.repository.CarRepository;
import org.adem.autotrade.service.CarService;
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
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final AnnouncementMapper announcementMapper;
    private final CarDetailMapper carDetailMapper;
    private final AdvantageMapper advantageMapper;

    @Override
    public void addCar(CarRequestDto carRequestDto) {
        Car car = carMapper.toCarEntity(carRequestDto);
        car.setAnnouncement(announcementMapper.toAnnouncementEntity(
                announcementMapper.fromResponseToRequest(carRequestDto.getAnnouncement()))
        );
        carRepository.save(car);
        log.info("Car added with id: {}", car.getId());
    }

    @Override
    public void updateCarByID(Integer id, CarRequestDto carRequestDto) {
        Optional<Car> existingCar = carRepository.findById(id);
        if (existingCar.isEmpty()) {
            throw new CarNotFoundException("Car not found");
        }
        existingCar.get().setCarDetail(carDetailMapper.toCarDetailEntity(
                carDetailMapper.fromResponseToRequest(carRequestDto.getCarDetail())));

        existingCar.get().setAdvantage(advantageMapper.dtoListToEntity(carRequestDto.getAdvantage()));

        existingCar.get().setBrand(carRequestDto.getBrand());
        existingCar.get().setPrice(carRequestDto.getPrice());
        existingCar.get().setModel(carRequestDto.getModel());
        existingCar.get().setYear(carRequestDto.getYear());

        carRepository.save(existingCar.get());
        log.info("Car updated with id:{}", existingCar.get().getId());
    }

    @Override
    public Page<CarResponseDto> getAllCars(Pageable pageable) {
        Page<Car> carList = carRepository.findAll(pageable);
        if (carList.isEmpty()) {
            throw new CarNotFoundException("Car not found");
        }
        return carList.map(carMapper::toCarResponse);
    }

    @Override
    public CarResponseDto getCarById(Integer id) {
        return carRepository.findById(id).stream()
                .map(carMapper::toCarResponse)
                .findFirst()
                .orElseThrow(() -> new CarNotFoundException("Car not found"));
    }

    @Override
    public Page<CarResponseDto> getCarsByModel(String model, Pageable pageable) {
        Page<Car> carPage = carRepository.getCarsByModel(model, pageable);
        if (carPage.isEmpty()) {
            throw new CarNotFoundException("Car not found");
        }
        return carPage.map(carMapper::toCarResponse);
    }

    @Override
    public void deleteCarByID(Integer id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isEmpty()) {
            throw new CarNotFoundException("Car not found");
        }
        carRepository.deleteById(id);
        log.info("Car deleted with id: {} ", car.get().getId());
    }

    @Override
    public Page<CarResponseDto> findBySpecification(String brand,
                                                    String model,
                                                    Integer year,
                                                    Pageable pageable) {

        Specification<Car> specification;

        specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (brand != null) {
                predicateList.add(criteriaBuilder.equal(root.get("brand"), brand));
            }
            if (model != null) {
                predicateList.add(criteriaBuilder.equal(root.get("model"), model));
            }
            if (year != null) {
                predicateList.add(criteriaBuilder.equal(root.get("year"), year));
            }

            Objects.requireNonNull(query).where(
                    criteriaBuilder.and(predicateList.toArray(predicateList.toArray(new Predicate[0])))
            );

            return query.getRestriction();
        };
        return carRepository.findAll(specification, pageable).map(carMapper::toCarResponse);
    }
}
