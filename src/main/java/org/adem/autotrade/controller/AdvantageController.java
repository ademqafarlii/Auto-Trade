package org.adem.autotrade.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.adem.autotrade.dto.request.AdvantageRequestDto;
import org.adem.autotrade.dto.response.AdvantageResponseDto;
import org.adem.autotrade.service.AdvantageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/advantage")
@RequiredArgsConstructor
public class AdvantageController {

    private final AdvantageService advantageService;


    @PostMapping("/add-advantage")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAdvantage(@RequestParam @Valid AdvantageRequestDto advantage) {
        advantageService.addAdvantage(advantage);
    }

    @PutMapping("/update-advantages-by-car-id")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateAdvantagesByCarID(@RequestParam Integer carID, @RequestBody @Valid AdvantageRequestDto advantage) {
        advantageService.updateAdvantagesByCarID(carID, advantage);
    }

    @PatchMapping("/partial-update-advantages-by-car-id")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void partialUpdateAdvantagesByCarID(@RequestParam Integer carID, @RequestBody @Valid AdvantageRequestDto advantage) {
        advantageService.updateAdvantagesByCarID(carID, advantage);
    }

    @GetMapping("/get-all-advantages-by-car-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    Page<AdvantageResponseDto> getAllAdvantagesByCarId(@PathVariable Integer id, Pageable pageable) {
        return advantageService.getAllAdvantagesByCarId(id, pageable);
    }

    @GetMapping("/delete-advantage-from-car-by")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAdvantageFromCar(@RequestParam Integer carId, @RequestParam Integer advantageId) {
        advantageService.deleteAdvantageFromCar(carId, advantageId);
    }

    @GetMapping("/find-by-spec")
    @ResponseStatus(HttpStatus.OK)
    public Page<AdvantageResponseDto> findBySpecification(@RequestParam(required = false) Boolean abs,
                                                          @RequestParam(required = false) Boolean luke,
                                                          @RequestParam(required = false) Boolean rainSensor,
                                                          @RequestParam(required = false) Boolean centralLock,
                                                          @RequestParam(required = false) Boolean parkingSensor,
                                                          @RequestParam(required = false) Boolean airConditioner,
                                                          @RequestParam(required = false) Boolean heatedSeats,
                                                          @PageableDefault(value = 12) Pageable pageable) {

        return advantageService.findBySpecification(
                abs, luke, rainSensor, centralLock, parkingSensor, airConditioner, heatedSeats, pageable);
    }
}
