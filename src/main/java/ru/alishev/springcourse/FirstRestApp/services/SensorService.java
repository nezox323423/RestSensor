package ru.alishev.springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.FirstRestApp.models.Sensor;
import ru.alishev.springcourse.FirstRestApp.repositories.SensorRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }
    @Transactional
    public void register(Sensor sensor){
        sensorRepository.save(sensor);
    }
    public Optional<Sensor> findByName(String name){
        return sensorRepository.findByName(name);
    }
    @Transactional
    public void delete(int id){
        sensorRepository.deleteById(id);

    }
}
