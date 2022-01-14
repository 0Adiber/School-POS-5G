package at.kaindorf.customerdb.controller;

import at.kaindorf.customerdb.data.CustomerRepository;
import at.kaindorf.customerdb.pojos.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        /*Optional<Customer> opt = customerRepository.findById(id);
        if(opt.isPresent())
            return ResponseEntity.ok(opt.get()); // ok() ruft build() intern auf
        return ResponseEntity.notFound().build();*/

        return ResponseEntity.of(customerRepository.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Customer>> getAllCustomers(@RequestParam("page") int pageNo, @RequestParam("size") int pageSize) {
        Pageable page = PageRequest.of(pageNo, pageSize, Sort.by("lastname"));
        return ResponseEntity.of(Optional.of(customerRepository.findAll(page)));
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            customer = customerRepository.save(customer);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(customer.getCustomerId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
