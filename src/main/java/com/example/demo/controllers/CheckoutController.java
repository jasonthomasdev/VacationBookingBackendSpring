package com.example.demo.controllers;

import com.example.demo.dao.CartRepository;
import com.example.demo.dto.Purchase;
import com.example.demo.dto.PurchaseResponse;
import com.example.demo.entities.Cart;
import com.example.demo.services.CheckoutService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;
    private CartRepository cartRepository;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {

        Cart cart = purchase.getCart();
        // DEBUG
        System.out.println("Received cart party size: " + cart.getPartySize());
        System.out.println("Received cart package price: " + cart.getPackagePrice());
        System.out.println("Received purchase party size: " + purchase.getPartySize());
        System.out.println("Received purchase package price: " + purchase.getPackagePrice());
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

        // DEBUG
        // System.out.println("orderTrackingNumber at /purchase: " + purchaseResponse.getOrderTrackingNumber());
        // System.out.println("Received party size: " + purchase.getPartySize());
        return purchaseResponse;
    }

}