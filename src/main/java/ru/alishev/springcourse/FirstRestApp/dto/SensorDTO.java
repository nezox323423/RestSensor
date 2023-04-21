package ru.alishev.springcourse.FirstRestApp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class SensorDTO {

    @NotEmpty(message = "Name should not be empty")
    @Size(min =3, max = 30, message = "Name should be from 3 to 30 characteristics")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
