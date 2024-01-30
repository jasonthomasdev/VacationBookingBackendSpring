package com.example.demo.dto;
// package com.example.demo.services;

import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class Purchase {
    private Cart cart;
    private Customer customer;
    private Set<CartItem> cartItems;

    private Integer partySize;
    private BigDecimal packagePrice;
}