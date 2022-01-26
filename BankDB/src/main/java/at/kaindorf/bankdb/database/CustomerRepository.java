package at.kaindorf.bankdb.database;

import at.kaindorf.bankdb.pojos.Customer;
import at.kaindorf.bankdb.pojos.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByLastnameContainingIgnoreCaseOrderByLastnameAscFirstnameAsc(String lastname);

    List<Customer> findByCity(String city);

    List<Customer> findByGender(Gender gender);

}