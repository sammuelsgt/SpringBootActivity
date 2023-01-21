package com.ecommerce.springbootactivity.service;


import com.ecommerce.springbootactivity.dto.CartsDto;
import com.ecommerce.springbootactivity.entity.Carts;
import com.ecommerce.springbootactivity.entity.Products;
import com.ecommerce.springbootactivity.exception.BadRequestException;
import com.ecommerce.springbootactivity.repository.CartsRepository;
import com.ecommerce.springbootactivity.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class CartsService {

    @Autowired
    private CartsRepository cartsRepository;


    public void addCart(int user_id, int product_id){
        Carts carts = cartsRepository.returnResult(product_id,user_id);
        if(carts == null){
            Carts newCart = new Carts();
            newCart.setCartId(newCart.getCartId());
            newCart.setUserId(user_id);
            newCart.setProductId(product_id);
            newCart.setQuantity(1);
            cartsRepository.save(newCart);
        } else if (carts != null) {
            carts.setCartId(carts.getCartId());
            carts.setProductId(product_id);
            carts.setQuantity(carts.getQuantity()+1);
            carts.setUserId(user_id);
            cartsRepository.save(carts);
        }
    }
    public void removeProductInCart(int user_id,int product_id){
        Carts carts = cartsRepository.returnResult(product_id,user_id);
        if(carts != null && carts.getQuantity() > 0){
            carts.setCartId(carts.getCartId());
            carts.setProductId(product_id);
            carts.setQuantity(carts.getQuantity()-1);
            carts.setUserId(user_id);
            cartsRepository.save(carts);
        }

    }

    public List<Object[]> findAll(int user_id) {

        return cartsRepository.productsInCart(user_id);
    }

    public Carts findByProductId(int product_id){
        return cartsRepository.findByProductId(product_id);
    }

    public Carts findByUserId(int user_id){
        return cartsRepository.findByUserId(user_id);
    };

}
