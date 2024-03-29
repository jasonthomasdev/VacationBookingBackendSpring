package com.example.demo.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "excursions")
@Getter
@Setter
public class Excursion {
    @Id
    @Column(name = "excursion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "excursion_title")
    private String excursion_title;

    @Column(name = "excursion_price")
    private BigDecimal excursion_price;

    @Column(name = "image_url")
    private String image_URL;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vacation_id")
    private Vacation vacation;

    @ManyToMany(mappedBy = "excursions")
    private Set<CartItem> cartItems;

    public Excursion(){}
}