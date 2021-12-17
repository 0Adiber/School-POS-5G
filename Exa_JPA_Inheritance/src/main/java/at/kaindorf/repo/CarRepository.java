package at.kaindorf.repo;

import at.kaindorf.pojos.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}