package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_first_name")
    private String firstName;

    @Column(name = "customer_last_name")
    private String lastName;

    private String address;

    @Column(name = "postal_code")
    private String postal_code;

    private String phone;

    @Temporal(TemporalType.TIMESTAMP)
    private Date create_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division;

    @OneToMany(mappedBy = "customer", cascade= CascadeType.ALL)
    private Set<Cart> carts = new HashSet<>();

    public void add(Cart cart) {

        if (cart != null) {

            if (carts == null) {
                carts = new HashSet<>();
            }

            carts.add(cart);
            cart.setCustomer(this);
        }
    }
}
