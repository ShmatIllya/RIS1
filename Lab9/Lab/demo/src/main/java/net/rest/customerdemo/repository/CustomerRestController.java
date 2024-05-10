package net.rest.customerdemo.repository;

import net.rest.customerdemo.model.Customer;
import net.rest.customerdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long customerId) {

        if (customerId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer customer = this.customerService.getById(customerId);

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> saveCustomer(@RequestBody @Valid Customer customer) {
        HttpHeaders headers = new HttpHeaders();
        try {


            if (customer == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            this.customerService.save(customer);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customer, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "many", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Customer>> saveAllCustomers(@RequestBody @Valid List<Customer> customers) {
        HttpHeaders headers = new HttpHeaders();
        try {

            if (customers == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            this.customerService.saveAll(customers);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customers, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> updateCustomer(@RequestBody @Valid Customer customer, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        try {
            if (customer == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            this.customerService.save(customer);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customer, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "many", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Customer>> updateAllCustomers(@RequestBody @Valid List<Customer> customers) {
        HttpHeaders headers = new HttpHeaders();
        try {
            if (customers == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            this.customerService.saveAll(customers);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customers, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id")  Long[] id) {
        try {
            List<Customer> customers = new ArrayList<>();
            for(int i = 0; i < id.length; i++)
            customers.add(this.customerService.getById(id[i]));

            if (customers == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            this.customerService.deleteAll(id);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = this.customerService.getAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}

