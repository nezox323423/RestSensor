package ru.alishev.springcourse.FirstRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstRestApp.dto.SensorDTO;
import ru.alishev.springcourse.FirstRestApp.models.Sensor;
import ru.alishev.springcourse.FirstRestApp.services.SensorService;
import ru.alishev.springcourse.FirstRestApp.util.MeasurementErrorResponse;
import ru.alishev.springcourse.FirstRestApp.util.SensorNotCreatedException;
import ru.alishev.springcourse.FirstRestApp.util.SensorsValidator;

import javax.validation.Valid;
import java.util.List;

import static ru.alishev.springcourse.FirstRestApp.util.ErrorsUtil.returnErrorsToClient;

/**
 * @author Neil Alishev
 */
@RestController // @Controller + @ResponseBody над каждым методом
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final SensorsValidator sensorsValidator;
    private final ModelMapper modelMapper;


    @Autowired
    public SensorController(SensorService sensorService,SensorsValidator sensorsValidator,  ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.sensorsValidator = sensorsValidator;
        this.modelMapper = modelMapper;
    }
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO,
                                                   BindingResult bindingResult){
        Sensor sensorToAdd = convertToSensor(sensorDTO);

        sensorsValidator.validate(sensorToAdd,bindingResult);

        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

           sensorService.register(sensorToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        sensorService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(SensorNotCreatedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }


}
