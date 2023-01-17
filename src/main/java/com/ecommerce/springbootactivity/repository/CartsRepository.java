package com.ecommerce.springbootactivity.repository;

import com.ecommerce.springbootactivity.entity.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartsRepository extends JpaRepository<Carts, Integer > {
}
