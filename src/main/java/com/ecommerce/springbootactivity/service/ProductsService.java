package com.ecommerce.springbootactivity.service;

import com.ecommerce.springbootactivity.dto.ProductsDto;
import com.ecommerce.springbootactivity.entity.Products;
import com.ecommerce.springbootactivity.entity.Users;
import com.ecommerce.springbootactivity.exception.BadRequestException;
import com.ecommerce.springbootactivity.repository.ProductsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    public List<Products> findAll() {
        return productsRepository.findAll();
    }

//    public Products save(Products products) {
//        return productsRepository.save(products);
//    }

    public static final String imageDir = "G:\\My Drive\\SpringBootActivity\\target\\classes\\static";
    //G:\My Drive\SpringBootActivity\target\classes\static
    public void save(ProductsDto productsDto, MultipartFile file, String imgName) throws IOException {
        Products products = new Products();


        String imgOrigLoc;
        if(!file.isEmpty()) {
            imgOrigLoc = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(imageDir,imgOrigLoc);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imgOrigLoc = imgName;
        }
        productsDto.setProductImage(imgOrigLoc);


        products.setProductName(productsDto.getProductName());
        products.setProductDescription(productsDto.getProductDescription());
        products.setProductQuantity(productsDto.getProductQuantity());
        products.setProductPrice((productsDto.getProductPrice()));
        products.setUserId((productsDto.getUserId()));
        products.setProductImage(productsDto.getProductImage());

        productsRepository.save(products);
    }

    public Products findById(int id) {
        return productsRepository.findById(id).orElseThrow(()-> new BadRequestException("Email cannot be found"));
    }

    public Products update(Products product) {
        return productsRepository.save(product);
    }

    public Products getProductById(int id) {
        Optional<Products> optionalProducts = productsRepository.findById(id);
        Products products = null;

        if (optionalProducts.isPresent()) {
            products = optionalProducts.get();
        } else {
            throw new RuntimeException(" Task not found, id ::" + id);
        }
        return products;
    }

    public void deleteProductById(int id) {
        productsRepository.deleteByproductId(id);
    }

    public List<Products> findAllById(List<Integer> ids) {
        return productsRepository.findAllById(ids);
    }
}
