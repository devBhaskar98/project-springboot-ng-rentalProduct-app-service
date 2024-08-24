package com.devproject.rentalproductservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devproject.rentalproductservice.entity.Product;
import com.devproject.rentalproductservice.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
    private ProductService productService;
	
	
	//add category
    @PostMapping("/")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product products = this.productService.addProduct(product);
        return ResponseEntity.ok(products);
    }

//	//get all categories
    @GetMapping("/")
    public ResponseEntity<?> getAllProduct() {
        return ResponseEntity.ok(this.productService.getProduct());
    }
	
}
