package com.ecommerce.springbootactivity.repository;

import com.ecommerce.springbootactivity.entity.Carts;
import com.ecommerce.springbootactivity.entity.Products;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CartsRepository extends JpaRepository<Carts, Integer > {

    Carts findByProductId(int productId);
    Carts findByUserId(int userId);
    @Query(nativeQuery = true,value= "select *" +
            " from Carts c where c.product_id=:product_id AND c.user_id=:user_id ")
    Carts returnResult(@Param("product_id") int product_id, @Param("user_id") int user_id );
    @Query(nativeQuery = true,
    value="select c.cart_id,c.product_id as cartproduct_id ,c.quantity as cartquantity, c.user_id as cartuser_id, " +
            " p.product_id, p.productname, p.productdescription, p.productquantity, " +
            " p.productprice, p.user_id, p.productimage from Carts c" +
            " inner join Products p on c.product_id = p.product_id where c.user_id=:user_id and c.quantity > 0 ")
    List<Object[]> productsInCart(@Param("user_id")int user_id);

//    value="select c.cart_id, c.user_id, p.productname, p.productprice, c.quantity , c.product_id from Carts c" +
//            " inner join Products p on c.product_id = p.product_id where c.user_id=:user_id"
//    @Modifying()
//    @Transactional
//    @Query(value = "update Carts c set c.quantity=:quantity + 1 where c.productId=:product_id AND c.userId=:user_id")
//    void updateQuantity(@Param("quantity") double quantity, @Param("product_id") int product_id, @Param("user_id") int user_Id );
}
