package com.ecommerce.springbootactivity.service;


import com.ecommerce.springbootactivity.dto.CartsDto;
import com.ecommerce.springbootactivity.entity.Carts;
import com.ecommerce.springbootactivity.exception.BadRequestException;
import com.ecommerce.springbootactivity.repository.CartsRepository;
import com.ecommerce.springbootactivity.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartsService {

    @Autowired
    private CartsRepository cartsRepository;


    public void addCart(CartsDto cartsDto ){
        Carts carts = new Carts();
        carts.setProductId(cartsDto.getProductId());
        Carts existsProduct = new Carts();

        System.out.print("PRODUCT ID CART SERVICE"+carts.getProductId());
        cartsRepository.save(carts);

    }

    public Carts findByProductId(int product_id){
        return cartsRepository.findByProductId(product_id);
    }
}
