package com.example.demo.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
@Data
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

    public void add(CartItem item) {

        if (item != null) {
            if (cartItems == null) {
                cartItems = new HashSet<>();
            }

            cartItems.add(item);
            item.setCart(this);
        }
    }
}