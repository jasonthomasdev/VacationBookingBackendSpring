package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "carts")
@EqualsAndHashCode(exclude = {"customer", "cartItems"})
@ToString(exclude = {"customer", "cartItems"})
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_id;

    @Column(name = "package_price")
    @JsonProperty("package_price")
    private BigDecimal packagePrice;

    @Column(name = "party_size")
    @JsonProperty("party_size")
    private Integer partySize;

    @Enumerated(EnumType.STRING)
    private StatusType status;

    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

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

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<CartItem> cartItems;

    public Cart(){}

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