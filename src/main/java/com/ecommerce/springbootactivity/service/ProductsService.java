package com.ecommerce.springbootactivity.service;

import com.ecommerce.springbootactivity.dto.ProductsDto;
import com.ecommerce.springbootactivity.entity.Products;
import com.ecommerce.springbootactivity.entity.Users;
import com.ecommerce.springbootactivity.exception.BadRequestException;
import com.ecommerce.springbootactivity.repository.ProductsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void save(Products products) {
        this.productsRepository.save(products);
    }


//    public void save(ProductsDto productsDto) {
//        Products products = new Products();
//        products.setProductName(productsDto.getProductName());
//        products.setProductDescription(productsDto.getProductDescription());
//        products.setProductQuantity(productsDto.getProductQuantity());
//        products.setProductPrice((productsDto.getProductPrice()));
//        products.setUserId((productsDto.getUserId()));
//        this.productsRepository.save(products);
//    }

//    public Products update(int id, Products products) {
//        products.setProductId(id);
//        return productsRepository.save(products);
//    }


    public void deleteProductById(int id) {
        productsRepository.deleteByproductId(id);
    }

    public Products getProductById(int id) {
    Optional<Products> optionalProducts = productsRepository.findById(id);
    Products products = null;

    if (optionalProducts.isPresent()) {
        products = optionalProducts.get();
    } else {
        throw new RuntimeException(" Product not found, id ::" + id);
    }
    return products;
}

    public ResponseEntity<Products> update(int id, Products productDetails) throws BadRequestException{
        Products updateProduct = productsRepository.findById(id).orElseThrow(()-> new BadRequestException("Product cannot be found"));
        updateProduct.setProductName(productDetails.getProductName());
        updateProduct.setProductDescription(productDetails.getProductDescription());
        updateProduct.setProductQuantity(productDetails.getProductQuantity());
        updateProduct.setProductPrice(productDetails.getProductPrice());
        final Products updatedProduct = productsRepository.save(updateProduct);
        return ResponseEntity.ok(updatedProduct);
    }

    public List<Products> findAllById(List<Integer> ids) {
        return productsRepository.findAllById(ids);
    }


}
