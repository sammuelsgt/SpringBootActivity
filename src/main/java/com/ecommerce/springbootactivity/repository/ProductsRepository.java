package com.ecommerce.springbootactivity.repository;

import com.ecommerce.springbootactivity.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer>
{

    void deleteAllByProductId(int productId);



}
