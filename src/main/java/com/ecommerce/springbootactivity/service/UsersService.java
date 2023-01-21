package com.ecommerce.springbootactivity.service;


import com.ecommerce.springbootactivity.dto.UsersDto;

import com.ecommerce.springbootactivity.entity.Users;

import com.ecommerce.springbootactivity.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

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
        return usersRepository.findByemail(email);
    }

}
