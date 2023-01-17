package com.ecommerce.springbootactivity.controller;

import com.ecommerce.springbootactivity.dto.ProductsDto;
import com.ecommerce.springbootactivity.dto.UsersDto;
import com.ecommerce.springbootactivity.entity.Users;
import com.ecommerce.springbootactivity.service.ProductsService;
import com.ecommerce.springbootactivity.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    ProductsService productsService;




    @GetMapping("/home")
    public String homePage(@CurrentSecurityContext(expression="authentication?.name")
                               String email, Model model){
        model.addAttribute("user", usersService.findUserByEmail(email));
        model.addAttribute("product" , productsService.findAll());
        System.out.println("test"+email);
        return "users/homepage";

    }




    @GetMapping("/login")
    public String login(){
        return "users/login";
    }



    @GetMapping("/logout")
    public String logout(){
        return "users/homepage";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model){

        UsersDto user = new UsersDto();
        model.addAttribute("user", user);
        return "users/register";
    }

    @PostMapping("/register/save")
    public String registration(@Validated @ModelAttribute("user") UsersDto usersDto,
                               BindingResult result,
                               Model model){
        Users existingUser = usersService.findUserByEmail(usersDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", usersDto);
            return "users/register";
        }

        usersService.saveUser(usersDto);
        return "redirect:/register?success";
    }






}
