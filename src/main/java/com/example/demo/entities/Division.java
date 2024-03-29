package com.example.demo.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "divisions")
@Getter
@Setter
public class Division {
    @Id
    @Column(name = "division_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "division")
    private String division_name;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private Date create_date;

    // @Temporal(TemporalType.TIMESTAMP)
    // private Date create_date;

    @UpdateTimestamp
    @Column(name = "last_update")
    private Date last_update;

    // @Temporal(TemporalType.TIMESTAMP)
    // private Date last_update;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "division")
    private Set<Customer> customers;

    public void addCustomer(Customer customer) {
        if (customers == null) {
            customers = new HashSet<>();
        }
        customers.add(customer);
        customer.setDivision(this);
    }

    public Division(){}

    public Division(String division_name, Country country){};

}
