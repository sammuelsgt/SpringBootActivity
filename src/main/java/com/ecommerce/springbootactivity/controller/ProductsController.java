package com.ecommerce.springbootactivity.controller;


import com.ecommerce.springbootactivity.dto.ProductsDto;
import com.ecommerce.springbootactivity.dto.UsersDto;
import com.ecommerce.springbootactivity.entity.Products;
import com.ecommerce.springbootactivity.service.ProductsService;
import com.ecommerce.springbootactivity.service.UsersService;
import jakarta.persistence.Access;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/web")
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @GetMapping("/products/{user_id}")
    public String showProductsForm(@PathVariable int user_id, ProductsDto productsDto, Model model) {
        productsDto.setUserId(user_id);
        System.out.println("USER_ID PRODUCT:" + productsDto.getUserId());
        model.addAttribute("product", productsDto);
        return "users/products/products";
    }

        // Add Product
        @PostMapping("/products/save")
        @Transactional
        public String saveProduct(@ModelAttribute(value = "product") ProductsDto productsDto,
                           @RequestParam("imageProduct") MultipartFile file,
                           @RequestParam("imgName") String imgName) throws IOException {
        productsService.save(productsDto, file, imgName);
        return "redirect:/web/products?success";
    }

    @GetMapping(path = "/products/delete/{id}")
    @Transactional
    public String deleteProduct(@PathVariable(value = "id") int id) {
        this.productsService.deleteProductById(id);
        return "redirect:/web/homepage?deleted";
    }

    // Update Task Form
    @GetMapping(path = "/products/update/{product_id}")
    public String updateTaskForm(@PathVariable(value = "product_id") int product_id, Model model) {
        Products products = productsService.getProductById(product_id);
        model.addAttribute("product", products);
        return "users/products/updateproducts";
    }


    @PostMapping("/products/saveProduct/{product_id}")
    public String updateProduct(@PathVariable(value = "product_id") int product_id,
                       @Validated @ModelAttribute("product") Products products,
                       @RequestParam(value = "imageProduct") MultipartFile file,
                       @RequestParam(value = "imgName") String imgName) throws IOException {
        products.setProductId(product_id);
        productsService.update(products, file, imgName);
        return "redirect:/web/products?success";
    }

}
