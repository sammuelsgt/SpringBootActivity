package com.ecommerce.springbootactivity.dto;

public class CartsDto {
    private int cartId;

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
