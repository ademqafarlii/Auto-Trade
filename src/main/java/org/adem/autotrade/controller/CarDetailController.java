package org.adem.autotrade.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.adem.autotrade.dto.request.CarDetailRequestDto;
import org.adem.autotrade.dto.response.CarDetailResponseDto;
import org.adem.autotrade.enums.Transmission;
import org.adem.autotrade.service.CarDetailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/car-detail")
@RequiredArgsConstructor
public class CarDetailController {

    private final CarDetailService carDetailService;


    @PostMapping("/add-car-details")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCarDetails(@RequestBody @Valid CarDetailRequestDto carDetail) {
        carDetailService.addCarDetails(carDetail);
    }

    @PutMapping("/update-car-details-by-car-id/{carId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCarDetailsByCarId(@PathVariable("carId") Integer id, @RequestBody @Valid CarDetailRequestDto carDetail) {
        carDetailService.updateCarDetailsByCarId(id, carDetail);
    }

    @PatchMapping("/partial-update-car-details-by-car-id/{carId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void partialUpdateCarDetailsByCarId(@PathVariable("carId") Integer id, @RequestBody @Valid CarDetailRequestDto carDetail) {
        carDetailService.updateCarDetailsByCarId(id, carDetail);
    }

    @GetMapping("/get-all-car-details")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Page<CarDetailResponseDto> getAllCarDetails(@PageableDefault(value = 12) Pageable pageable) {
        return carDetailService.getAllCarDetails(pageable);
    }

    @GetMapping("/get-car-details-by-car-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarDetailResponseDto getCarDetailsByCarId(@PathVariable Integer id) {
        return carDetailService.getCarDetailsByCarId(id);
    }

    @DeleteMapping("/delete-car-details-by-car-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCarDetailsByCarId(@PathVariable Integer id) {
        carDetailService.deleteCarDetailsByCarId(id);
    }

    @GetMapping("/find-by-spec")
    @ResponseStatus(HttpStatus.OK)
    public Page<CarDetailResponseDto> findBySpecification(@RequestParam(required = false) Long mileage,
                                                          @RequestParam(required = false) Transmission transmission,
                                                          @RequestParam(required = false) Boolean isNew,
                                                          @RequestParam(required = false) String vanType,
                                                          @RequestParam(required = false) String color,
                                                          @PageableDefault(value = 12) Pageable pageable) {
        return carDetailService.findBySpecification(mileage, transmission, isNew, vanType, color, pageable);
    }
}
