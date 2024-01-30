package com.example.demo.services;

import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.dto.Purchase;
import com.example.demo.dto.PurchaseResponse;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.math.BigDecimal;
import java.util.Set;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    private CustomerRepository customerRepository;
    private CartRepository cartRepository;

    @Autowired // This annotation is not necessary when there is only one constructor, but added for clarity
    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        String orderTrackingNumber;

        /*
        if (purchase.getCart() == null) {
            throw new IllegalArgumentException("Cart cannot be null");
        }
        */
        Cart cart = purchase.getCart();
        System.out.println("Party Size in Service: " + cart.getPartySize());
        System.out.println("Package Price in Service: " + cart.getPackagePrice());

        System.out.println("DEBUG -  orderTrackingnumber before: " + cart.getOrderTrackingNumber());
        // Check if the cart is empty
        if (cart.getPackagePrice().intValue() == 0) {
            orderTrackingNumber = "Cart is empty. Purchase cancelled.";
            System.out.println("DEBUG - Caught in the IF STATEMENT");
        } else {
            orderTrackingNumber = generateOrderTrackingNumber();
            System.out.println("DEBUG - Caught in the ELSE STATEMENT");
        }

        // DEBUG
        System.out.println("cart.getCartItems() = " + cart.getCartItems());
        // System.out.println("cart.getCartItems().isEmpty() = " + cart.getCartItems().isEmpty());

        cart.setOrderTrackingNumber(orderTrackingNumber);

        System.out.println("DEBUG -  orderTrackingnumber after: " + cart.getOrderTrackingNumber());
        Integer partySize = purchase.getPartySize();
        BigDecimal packagePrice = purchase.getPackagePrice();



        // DEBUG
        // purchase.setPartySize(555);
        // System.out.println("purchase.getPartySize(): " + partySize);

        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(item -> cart.add(item));

        cart.setCartItems(purchase.getCartItems());
        cart.setCustomer(purchase.getCustomer());
        // cart.setCreate_date(purchase.getCart().getCreate_date());
        // cart.setLast_update(purchase.getCart().getLast_update());
        // cart.setPartySize(purchase.getPartySize());
        // cart.setPackagePrice(purchase.getPackagePrice());

        cart.setStatus(StatusType.ordered);

        // Save cart to cartRepository
        cartRepository.save(cart);

        Customer customer = purchase.getCustomer();
        customer.add(cart);

        // Uncomment for debugging purposes
        System.out.println("Party Size: " + cart.getPartySize());
        // System.out.println("Package Price: " + cart.getPackagePrice());

        customerRepository.save(customer);

        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
