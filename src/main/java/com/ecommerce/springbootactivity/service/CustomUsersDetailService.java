package com.ecommerce.springbootactivity.service;


import com.ecommerce.springbootactivity.entity.Role;
import com.ecommerce.springbootactivity.entity.Users;
import com.ecommerce.springbootactivity.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUsersDetailService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepository.findByemail(email);

        if(user != null){
            return new User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));

        }else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> types) {
        Collection < ? extends GrantedAuthority> mapRoles = types.stream()
                .map(type -> new SimpleGrantedAuthority(type.getRoleName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}
