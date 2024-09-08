package org.adem.autotrade.controller;

import lombok.RequiredArgsConstructor;
import org.adem.autotrade.model.CarDetail;
import org.adem.autotrade.service.CarDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car-detail")
@RequiredArgsConstructor
public class CarDetailController {

    private final CarDetailService carDetailService;


    @PostMapping("/add-car-details")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCarDetails(@RequestBody CarDetail carDetail) {
        carDetailService.addCarDetails(carDetail);
    }

    @PutMapping("/update-car-details-by-car-id/{carId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCarDetailsByCarId(@PathVariable("carId") Integer id,@RequestBody CarDetail carDetail) {
        carDetailService.updateCarDetailsByCarId(id, carDetail);
    }

    @PatchMapping("/partial-update-car-details-by-car-id/{carId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void partialUpdateCarDetailsByCarId(@PathVariable("carId") Integer id,@RequestBody CarDetail carDetail) {
        carDetailService.updateCarDetailsByCarId(id, carDetail);
    }

    @GetMapping("/get-all-car-details")
    @ResponseStatus(HttpStatus.OK)
    public List<CarDetail> getAllCarDetails() {
        return carDetailService.getAllCarDetails();
    }

    @GetMapping("/get-car-details-by-car-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarDetail getCarDetailsByCarId(@PathVariable Integer id) {
        return carDetailService.getCarDetailsByCarId(id);
    }

    @DeleteMapping("/delete-car-details-by-car-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCarDetailsByCarId(@PathVariable Integer id) {
        carDetailService.deleteCarDetailsByCarId(id);
    }
}
