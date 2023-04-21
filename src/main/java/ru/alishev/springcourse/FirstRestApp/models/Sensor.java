package ru.alishev.springcourse.FirstRestApp.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "sensor")
public class Sensor implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min =3, max = 30, message = "Name should be from 3 to 30 characteristics")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime created_at;

}
