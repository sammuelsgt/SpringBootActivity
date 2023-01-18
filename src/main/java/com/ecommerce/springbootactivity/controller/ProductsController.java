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
    @PostMapping("/products/save")
    public String save(@Validated @ModelAttribute("product") ProductsDto productsDto
                       ){
            productsService.save(productsDto);
        return "redirect:/web/products?success";
    }



}
