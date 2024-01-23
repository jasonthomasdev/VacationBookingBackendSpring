package com.example.demo.bootstrap;

import com.example.demo.dao.CountryRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Country;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final DivisionRepository divisionRepository;
    @Autowired
    private final CountryRepository countryRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository, CountryRepository countryRepository){
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
        this.countryRepository = countryRepository;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Country usa = new Country("USA");
        countryRepository.save(usa);
        countryRepository.flush();

        Division virginia = new Division("Virginia", usa);
        virginia.setCountry(usa);
        divisionRepository.save(virginia);

        Customer michaelJordan = new Customer("Michael", "Jordan", "111 Main St", "11111", "1121121122", virginia);
        virginia.addCustomer(michaelJordan);
        customerRepository.save(michaelJordan);

        Customer billGates = new Customer("Bill", "Gates", "222 Oak Ln", "22222", "2232232233", virginia);
        virginia.addCustomer(billGates);
        customerRepository.save(billGates);

        Customer oprahWinfrey = new Customer("Oprah", "Winfrey", "333 Sunny Ter", "33333", "3343343344", virginia);
        virginia.addCustomer(oprahWinfrey);
        customerRepository.save(oprahWinfrey);

        Customer steveWozniak = new Customer("Steve", "Wozniak", "444 Happiness Way", "44444", "4454454455", virginia);
        virginia.addCustomer(steveWozniak);
        customerRepository.save(steveWozniak);

        Customer clarkKent = new Customer("Clark", "Kent", "555 Krypton Cir", "55555", "5565565566", virginia);
        virginia.addCustomer(clarkKent);
        customerRepository.save(clarkKent);

        System.out.println("Number of Customers: " + customerRepository.findAll().size());
    }

}
