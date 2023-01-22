package com.ecommerce.springbootactivity.controller;

import com.ecommerce.springbootactivity.dto.ProductsDto;
import com.ecommerce.springbootactivity.entity.Products;
import com.ecommerce.springbootactivity.service.ProductsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/web")
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @GetMapping("/products/{user_id}")
    public String showProductsForm(@PathVariable int user_id, ProductsDto productsDto, Model model) {
      try {
          productsDto.setUserId(user_id);
          model.addAttribute("product", productsDto);
          return "users/products/products";
      }catch (Exception e){
          return "redirect:/web/login";
      }

    }
    // Add Product
    @PostMapping("/products/save")
    @Transactional
    public String saveProduct(@ModelAttribute(value = "product") ProductsDto productsDto,
                              @RequestParam("imageProduct") MultipartFile file,
                              @RequestParam("imgName") String imgName) throws IOException {
        productsService.save(productsDto, file, imgName);
        return "redirect:/web/home?product-added";
    }

    @GetMapping ("/products/delete/{productId}")
    @Transactional
    public String deleteProduct(@PathVariable int productId) {
        System.out.println(productId);
        productsService.deleteProductById(productId);
        return "redirect:/web/home?product-deleted";
    }

    // Update Task Form
    @GetMapping(path = "/products/update/{product_id}")
    public String updateProductForm(@PathVariable(value = "product_id") int product_id, Model model) {
        Products products = productsService.getProductById(product_id);
        model.addAttribute("product", products);
        return "users/products/updateproducts";
    }

    @PostMapping("/products/saveProduct/{product_id}")
    public String updateProduct(@PathVariable(value = "product_id") int product_id,
                                @Validated @ModelAttribute("product") Products productsDto,
                                @RequestParam(value = "imageProduct") MultipartFile file,
                                @RequestParam(value = "imgName") String imgName) throws IOException {

        productsDto.setProductId(product_id);
        productsService.update(productsDto, file, imgName);
        return "redirect:/web/home?product-updated";
    }


}
