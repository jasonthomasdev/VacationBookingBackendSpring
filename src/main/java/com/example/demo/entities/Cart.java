package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "carts")
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_id;

    @Column(name = "package_price")
    private BigDecimal packagePrice;

    @Column(name = "party_size")
    private Integer partySize;

    @Enumerated(EnumType.STRING)
    private StatusType status;

    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date create_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart")
    private Set<CartItem> cartItems;
}