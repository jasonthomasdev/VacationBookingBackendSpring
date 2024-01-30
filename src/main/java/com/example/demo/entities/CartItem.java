package com.example.demo.entities;

import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "cart_items")
@EqualsAndHashCode(exclude = {"cart", "vacation", "excursions"})
@ToString(exclude = {"cart", "vacation", "excursions"})
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_item_id;

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
    // (cascade= CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "vacation_id")
    private Vacation vacation;

    @ManyToMany
    @JoinTable(
            name = "excursion_cartitem",
            joinColumns = @JoinColumn(name = "cart_item_id"),
            inverseJoinColumns = @JoinColumn(name = "excursion_id")
    )
    private Set<Excursion> excursions;

    public CartItem(){}

}
