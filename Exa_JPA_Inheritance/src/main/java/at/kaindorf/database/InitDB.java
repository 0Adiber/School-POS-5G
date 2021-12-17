package at.kaindorf.database;

import at.kaindorf.pojos.Car;
import at.kaindorf.pojos.Truck;
import at.kaindorf.repo.CarRepository;
import at.kaindorf.repo.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitDB {

    @Autowired
    private CarRepository carRepo;

    @Autowired
    private TruckRepository truckRepo;

    @PostConstruct
    public void init() {
        carRepo.save(new Car("Volvo", "electric", 5));
        truckRepo.save(new Truck("Mercedes", 26_000.0, 340));
    }

}
