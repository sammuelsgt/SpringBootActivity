package com.ecommerce.springbootactivity.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "carts")
public class Carts {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cart_id;

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

}
