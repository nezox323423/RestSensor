package ru.alishev.springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.FirstRestApp.models.Measurements;
import ru.alishev.springcourse.FirstRestApp.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }
    public List<Measurements> findAll (Measurements measurements){
       return measurementRepository.findAll();
    }
    @Transactional
    public void addMeasurement(Measurements measurements){
        enrichmeasurement(measurements);
        measurementRepository.save(measurements);
    }

    private void enrichmeasurement(Measurements measurements) {
        measurements.setSensor(sensorService.findByName(measurements.getSensor()
                .getName()).get());

        measurements.setMeasurementDateTime(LocalDateTime.now());
    }
}
