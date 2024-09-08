package org.adem.autotrade.controller;

import lombok.RequiredArgsConstructor;
import org.adem.autotrade.model.Advantage;
import org.adem.autotrade.service.AdvantageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advantage")
@RequiredArgsConstructor
public class AdvantageController {

    private final AdvantageService advantageService;


    @PostMapping("/add-advantage")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAdvantage(@RequestParam Advantage advantage) {
        advantageService.addAdvantage(advantage);
    }

    @PutMapping("/update-advantages-by-car-id")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateAdvantagesByCarID(@RequestParam Integer carID, @RequestBody Advantage advantage) {
        advantageService.updateAdvantagesByCarID(carID, advantage);
    }

    @PatchMapping("/partial-update-advantages-by-car-id")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void partialUpdateAdvantagesByCarID(@RequestParam Integer carID, @RequestBody Advantage advantage) {
        advantageService.updateAdvantagesByCarID(carID, advantage);
    }

    @GetMapping("/get-all-advantages-by-car-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Advantage> getAllAdvantagesByCarId(@PathVariable Integer id) {
        return advantageService.getAllAdvantagesByCarId(id);
    }

    @GetMapping("/delete-advantage-from-car-by")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAdvantageFromCar(@RequestParam Integer carId, @RequestParam Integer advantageId) {
        advantageService.deleteAdvantageFromCar(carId, advantageId);
    }
}
