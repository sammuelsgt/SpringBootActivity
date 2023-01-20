package com.ecommerce.springbootactivity.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "carts")
public class Carts {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private int cartId;

    @Column(name="product_id")
    private int productId;


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cart_id) {
        this.cartId = cart_id;
    }

}
