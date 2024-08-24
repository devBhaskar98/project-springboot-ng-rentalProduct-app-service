package com.devproject.rentalproductservice.service;

import java.util.Set;
import com.devproject.rentalproductservice.entity.Product;

public interface ProductService {
	
	public Product addProduct(Product category);
	
	public Set<Product> getProduct();
}
