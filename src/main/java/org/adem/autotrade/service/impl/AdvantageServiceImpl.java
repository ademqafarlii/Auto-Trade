package org.adem.autotrade.service.impl;

import lombok.RequiredArgsConstructor;
import org.adem.autotrade.exception.AdvantageNotFoundException;
import org.adem.autotrade.model.Advantage;
import org.adem.autotrade.repository.AdvantageRepository;
import org.adem.autotrade.service.AdvantageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvantageServiceImpl implements AdvantageService {

    private final AdvantageRepository advantageRepository;

    @Override
    public void addAdvantage(Advantage advantage) {
        advantageRepository.save(advantage);
    }

    @Override
    public void updateAdvantagesByCarID(Integer carID,Advantage advantage) {
        List<Advantage> advantages = advantageRepository.findByCarID(carID);

        if (advantages.isEmpty()){
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
    public List<Advantage> getAllAdvantagesByCarId(Integer id) {
       List<Advantage> advantages = advantageRepository.findByCarID(id);
       if (advantages.isEmpty()){
           throw new AdvantageNotFoundException("Advantage not found");
       }
       return advantages;
    }

    @Override
    public void deleteAdvantageFromCar(Integer carId, Integer advantageId) {
        List<Advantage> advantages = advantageRepository.findByCarIdAndAdvantageId(carId, advantageId);
        if (advantages.isEmpty()){
            throw new AdvantageNotFoundException("Advantage not found");
        }
        advantageRepository.deleteByAdvantageIdAndCarID(carId, advantageId);
    }
}
