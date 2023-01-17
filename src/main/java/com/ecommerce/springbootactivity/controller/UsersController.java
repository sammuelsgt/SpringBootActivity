package com.ecommerce.springbootactivity.controller;

import com.ecommerce.springbootactivity.dto.UsersDto;
import com.ecommerce.springbootactivity.entity.Users;
import com.ecommerce.springbootactivity.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class UsersController {

    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/home")
    public String homePage(){

        return "users/homepage";

    }

    @GetMapping("/login")
    public String login(){
        return "users/login";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
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
