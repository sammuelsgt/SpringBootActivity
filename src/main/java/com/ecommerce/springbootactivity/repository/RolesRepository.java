package com.ecommerce.springbootactivity.repository;

import com.ecommerce.springbootactivity.entity.Role;
import com.ecommerce.springbootactivity.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {

    Role findByroleName(String name);
}
