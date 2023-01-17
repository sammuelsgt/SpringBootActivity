package com.ecommerce.springbootactivity.service;

import com.ecommerce.springbootactivity.dto.ProductsDto;
import com.ecommerce.springbootactivity.entity.Products;
import com.ecommerce.springbootactivity.entity.Users;
import com.ecommerce.springbootactivity.exception.BadRequestException;
import com.ecommerce.springbootactivity.repository.ProductsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    public List<Products> findAll() {
        return productsRepository.findAll();
    }


    public Products findById(int id) {
        return productsRepository.findById(id).orElseThrow(()-> new BadRequestException("Email cannot be found"));
    }


    public void save(ProductsDto productsDto) {
        Products products = new Products();
        products.setProductName(productsDto.getProductName());
        products.setProductDescription(productsDto.getProductDescription());
        products.setProductQuantity(productsDto.getProductQuantity());
        products.setProductPrice((productsDto.getProductPrice()));



        productsRepository.save(products);
    }


    public Products update(int id, Products products) {
        products.setProductId(id);
        return productsRepository.save(products);
    }


    public void delete(int id) {
        productsRepository.deleteById(id);
    }


    public List<Products> findAllById(List<Integer> ids) {
        return productsRepository.findAllById(ids);
    }
}
