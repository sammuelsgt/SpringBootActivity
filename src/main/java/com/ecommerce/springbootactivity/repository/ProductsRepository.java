package com.ecommerce.springbootactivity.repository;

import com.ecommerce.springbootactivity.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer>
{
    List<Products> findAllByUserId(int userId);
    void deleteAllByProductId(int productId);





}
