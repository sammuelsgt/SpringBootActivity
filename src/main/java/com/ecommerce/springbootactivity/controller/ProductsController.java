package com.ecommerce.springbootactivity.controller;


import com.ecommerce.springbootactivity.dto.ProductsDto;
import com.ecommerce.springbootactivity.dto.UsersDto;
import com.ecommerce.springbootactivity.entity.Products;
import com.ecommerce.springbootactivity.service.ProductsService;
import com.ecommerce.springbootactivity.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web")
public class ProductsController
{
    private ProductsService productsService;
    @GetMapping("/products/{user_id}")
    public String showProductsForm(@PathVariable int user_id, Model model){

        ProductsDto productsDto = new ProductsDto();

        productsDto.setUserId(user_id);
        model.addAttribute("product", productsDto);
        return "users/products/products";
    }
    @PostMapping("/products/save")
    public String save(@Validated @ModelAttribute("product") ProductsDto productsDto
                       ){
            productsService.save(productsDto);
        return "redirect:/products?success";
    }



}
