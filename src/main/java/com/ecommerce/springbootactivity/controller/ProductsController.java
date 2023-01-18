package com.ecommerce.springbootactivity.controller;


import com.ecommerce.springbootactivity.dto.ProductsDto;
import com.ecommerce.springbootactivity.dto.UsersDto;
import com.ecommerce.springbootactivity.entity.Products;
import com.ecommerce.springbootactivity.repository.ProductsRepository;
import com.ecommerce.springbootactivity.service.ProductsService;
import com.ecommerce.springbootactivity.service.UsersService;
import jakarta.persistence.Access;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/web")
public class ProductsController
{
    @Autowired
    private ProductsService productsService;

    @GetMapping("/products/{user_id}")
    public String showProductsForm(@PathVariable int user_id, ProductsDto productsDto , Model model){
        productsDto.setUserId(user_id);
        System.out.println("USER_ID PRODUCT:"+productsDto.getUserId());
        model.addAttribute("product", productsDto);
        return "users/products/products";
    }

    @PostMapping(path = "/products/save")
    public String save(@ModelAttribute("product") Products products) {
        productsService.save(products);
        return "redirect:/web/homepage?productAdded";
    }

    //Update
    @GetMapping(path = "/products/update/{product_id}")
    public String updateTaskForm(@PathVariable(value = "product_id") int product_id, Model model)  {
        Products products = productsService.getProductById(product_id);
        model.addAttribute("product", products);
        return "users/products/updateproducts";
    }

//    @PostMapping(path = "/products/updateProd/{product_id}")
//    public String updateProduct(@PathVariable int product_id, @ModelAttribute("updateProduct") @RequestBody Products products) {
//        productsService.update(product_id, products);
//        return "redirect: /web/homepage?updateProduct";
//    }

    @PutMapping(path = "/products/updateProd/{product_id}")
    public String updateProduct(@PathVariable(value = "product_id") int product_id, @Validated @RequestBody Products productDetails) {
        productsService.update(product_id, productDetails);
        return "redirect: /web/homepage?updateProduct";
    }

    //Delete
    @GetMapping(path = "/products/delete/{id}")
    @Transactional
    public String deleteProduct(@PathVariable(value = "id") int id) {
        this.productsService.deleteProductById(id);
        return "redirect: /web/homepage?deleted";
    }
}
