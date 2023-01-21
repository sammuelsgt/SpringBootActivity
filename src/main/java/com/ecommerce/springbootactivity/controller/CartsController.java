package com.ecommerce.springbootactivity.controller;


import com.ecommerce.springbootactivity.dto.CartsDto;
import com.ecommerce.springbootactivity.dto.ProductsDto;
import com.ecommerce.springbootactivity.dto.UsersDto;
import com.ecommerce.springbootactivity.entity.Users;
import com.ecommerce.springbootactivity.service.CartsService;
import com.ecommerce.springbootactivity.service.UsersService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web")
public class CartsController {

    @Autowired
    private CartsService cartsService;
    @Autowired
    private UsersService usersService;

    @GetMapping("/cartlist")
    public String showCartList(@CurrentSecurityContext(expression="authentication?.name")
                                   String email, Model model) {

        model.addAttribute("carts",
                cartsService.findAll(usersService.findUserByEmail(email).getUserId()));

                return "users/carts/carts";
    }

    @PostMapping("/add-cart/{product_id}")
    public String addToCart(@PathVariable int product_id,
                            @CurrentSecurityContext(expression="authentication?.name")
    String email){


        cartsService.addCart(usersService.findUserByEmail(email).getUserId(),product_id);
        return "redirect:/web/home";
    }

    @PostMapping("/remove-cart/{product_id}")
    public String removeFromCart(@PathVariable int product_id,
                                 @CurrentSecurityContext(expression = "authentication?.name") String email){

        cartsService.removeProductInCart(usersService.findUserByEmail(email).getUserId(),product_id);

        return "redirect:/web/cartlist?item-removed";
    }






}
