package com.ecommerce.springbootactivity.service;


import com.ecommerce.springbootactivity.dto.UsersDto;
import com.ecommerce.springbootactivity.entity.Products;
import com.ecommerce.springbootactivity.entity.Role;
import com.ecommerce.springbootactivity.entity.Users;
import com.ecommerce.springbootactivity.exception.BadRequestException;
import com.ecommerce.springbootactivity.repository.RolesRepository;
import com.ecommerce.springbootactivity.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(UsersDto usersDto){
        Users users = new Users();
        users.setFirstName(usersDto.getFirstName());
        users.setLastName(usersDto.getLastName());
        users.setEmail(usersDto.getEmail());
        users.setPassword(passwordEncoder.encode(usersDto.getPassword()));
        users.setRoleId(usersDto.getRoleId());

        usersRepository.save(users);
    }

    public Users findUserByEmail(String email){

        System.out.println("UserController:" +usersRepository.findByemail(email).getRoleId());
        return usersRepository.findByemail(email);
    }

    public Users findUserById(int user_id) throws BadRequestException {
        return usersRepository.findById(user_id).orElseThrow(() ->new BadRequestException("User cannot be found"));
    }

    public boolean checkUserRole(int role_id){
        if(usersRepository.findByroleId(role_id).getRoleId() == 1){
            return true;
        }

        return false;

    }



}
