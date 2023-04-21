package ru.alishev.springcourse.FirstRestApp.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class MeasurementDTO {

    @Size(min = -100, max = 100, message = "Value should be from -100 to 100 characteristics")
    @NotNull
    private double value;

    @NotNull
    private boolean raining;

    @NotNull
    private LocalDateTime measurementDateTime;

    @NotNull
    private SensorDTO sensor;

}
