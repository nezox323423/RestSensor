package ru.alishev.springcourse.FirstRestApp.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "measurements")
public class Measurements {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @Size(min = -100, max = 100, message = "Value should be from -100 to 100 characteristics")
    @NotNull
    private double value;

    @Column(name = "raining")
    @NotNull
    private boolean raining;

    @Column(name = "measurement_date_time")
    @NotNull
    private LocalDateTime measurementDateTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sensor" , referencedColumnName = "name")
    private Sensor sensor;

}
