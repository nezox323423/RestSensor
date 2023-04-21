package ru.alishev.springcourse.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alishev.springcourse.FirstRestApp.models.Measurements;

public interface MeasurementRepository extends JpaRepository<Measurements,Integer > {

}
