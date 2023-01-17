package com.ecommerce.springbootactivity.service;


import com.ecommerce.springbootactivity.dto.UsersDto;
import com.ecommerce.springbootactivity.entity.Role;
import com.ecommerce.springbootactivity.entity.Users;
import com.ecommerce.springbootactivity.repository.RolesRepository;
import com.ecommerce.springbootactivity.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;


    public void saveUser(UsersDto usersDto){
        Users users = new Users();
        users.setFirstName(usersDto.getFirstName());
        users.setLastName(usersDto.getLastName());
        users.setEmail(usersDto.getEmail());
        users.setPassword(usersDto.getPassword());

        Role role = rolesRepository.findByroleName("ROLE_BUYER");
        users.setRoleId(role.getRoleId());
        users.setRoles(Arrays.asList(role));
        usersRepository.save(users);
    }

    public Users findUserByEmail(String email){
        return usersRepository.findByemail(email);
    }


    public void register(Users users){
        try {
            if(usersRepository.findByemail(users.getEmail()) != null){
                this.usersRepository.save(users);
            }
        }
        catch ( Exception e){

        }
    }


}
