package ru.alishev.springcourse.FirstRestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alishev.springcourse.FirstRestApp.models.Sensor;
import ru.alishev.springcourse.FirstRestApp.services.SensorService;

@Component
public class SensorsValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorsValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Sensor.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Sensor sensor = (Sensor) o;

        //посмотреть есть ли такой сенсор в БД
    if (sensorService.findByName(sensor.getName()).isPresent())
        errors.rejectValue("name", "Уже есть сенсор с таким названием!");
    }
}
