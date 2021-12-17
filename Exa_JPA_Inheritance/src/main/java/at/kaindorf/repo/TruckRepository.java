package at.kaindorf.repo;

import at.kaindorf.pojos.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<Truck, Long> {
}