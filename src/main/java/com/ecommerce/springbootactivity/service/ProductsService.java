package com.ecommerce.springbootactivity.service;
import com.ecommerce.springbootactivity.dto.ProductsDto;
import com.ecommerce.springbootactivity.entity.Products;
import com.ecommerce.springbootactivity.exception.BadRequestException;
import com.ecommerce.springbootactivity.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URLDecoder;
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

    public static final String imageDir = "G:\\My Drive\\SpringBootActivity\\target\\classes\\static";

    public String img(MultipartFile file, String imgName) throws IOException {
       String imgOrigLoc;

        if(!file.isEmpty()) {
            imgOrigLoc = URLDecoder.decode( file.getOriginalFilename(), "UTF-8");
            Path fileNameAndPath = Paths.get(imageDir,imgOrigLoc);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imgOrigLoc = imgName;
        }

        return imgOrigLoc;
    }

    public Products save(ProductsDto productsDto, MultipartFile file, String imgName) throws IOException {
        Products products = new Products();
        productsDto.setProductImage(img(file, imgName));
        products.setProductName(productsDto.getProductName());
        products.setProductDescription(productsDto.getProductDescription());
        products.setProductQuantity(productsDto.getProductQuantity());
        products.setProductPrice((productsDto.getProductPrice()));
        products.setUserId((productsDto.getUserId()));
        products.setProductImage(productsDto.getProductImage());
        return productsRepository.save(products);
    }

    public Products update(Products productsDto, MultipartFile file, String imgName) throws IOException {
        productsDto.setProductImage(img(file, imgName));

        return productsRepository.save(productsDto);
    }

    public Products getProductById(int id) {
        Optional<Products> optionalProducts = productsRepository.findById(id);
        Products products;

        if (optionalProducts.isPresent()) {
            products = optionalProducts.get();
        } else {
            throw new RuntimeException(" Task not found, id ::" + id);
        }
        return products;
    }

    public void deleteProductById(int product_id) {
        Products products = productsRepository.findById(product_id).orElseThrow();
        productsRepository.delete(products);
    }




}
