package org.adem.autotrade.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.adem.autotrade.dto.request.CarRequestDto;
import org.adem.autotrade.dto.response.CarResponseDto;
import org.adem.autotrade.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping("/add-car")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCar(@RequestBody @Valid CarRequestDto car) {
        carService.addCar(car);
    }

    @PutMapping("/update-car-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCarByID(@PathVariable Integer id, @RequestBody @Valid CarRequestDto car) {
        carService.updateCarByID(id, car);
    }

    @PatchMapping("/partial-update-car-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void partialUpdateCarByID(@PathVariable Integer id, @RequestBody @Valid CarRequestDto car) {
        carService.updateCarByID(id, car);
    }

    @GetMapping("/get-all-cars")
    @ResponseStatus(HttpStatus.OK)
    public Page<CarResponseDto> getAllCars(@PageableDefault(value = 12) Pageable pageable) {
        return carService.getAllCars(pageable);
    }

    @GetMapping("/get-car-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarResponseDto getCarById(@PathVariable Integer id) {
        return carService.getCarById(id);
    }

    @GetMapping("/get-cars-by-model")
    @ResponseStatus(HttpStatus.OK)
    public Page<CarResponseDto> getCarsByModel(@RequestParam String model,
                                               @PageableDefault(value = 12) Pageable pageable) {
        return carService.getCarsByModel(model, pageable);
    }

    @DeleteMapping("/delete-car-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCarByID(@PathVariable Integer id) {
        carService.deleteCarByID(id);
    }

    @GetMapping("/find-by-spec")
    @ResponseStatus(HttpStatus.OK)
    public Page<CarResponseDto> findBySpecification(@RequestParam(required = false) String brand,
                                             @RequestParam(required = false) String model,
                                             @RequestParam(required = false) Integer year,
                                             @PageableDefault(value = 12) Pageable pageable) {
        return carService.findBySpecification(brand, model, year, pageable);
    }
}
