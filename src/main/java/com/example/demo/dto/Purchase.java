package com.example.demo.dto;
// package com.example.demo.services;

import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Cart cart;
    private Customer customer;
    private Set<CartItem> cartItems;
}