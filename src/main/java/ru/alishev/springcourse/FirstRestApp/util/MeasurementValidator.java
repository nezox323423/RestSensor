package ru.alishev.springcourse.FirstRestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alishev.springcourse.FirstRestApp.models.Measurements;
import ru.alishev.springcourse.FirstRestApp.services.SensorService;

public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Measurements.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurements measurements = (Measurements) o;

        if (measurements.getSensor() == null) {
            return;
        }
        if (sensorService.findByName(measurements.getSensor().getName()).isEmpty());
            errors.rejectValue("sensor", "Нет зарегистрированного сенсора с таким именем!");
    }

}
