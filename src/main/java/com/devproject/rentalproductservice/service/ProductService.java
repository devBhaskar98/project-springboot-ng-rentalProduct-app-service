package com.devproject.rentalproductservice.service;

import java.util.Set;
import com.devproject.rentalproductservice.entity.Product;

public interface ProductService {
	
	public Product addProduct(Product product);
	
	public Product updateProduct(Product product);
	
	public Set<Product> getProduct();
	
	public Product getProduct(Integer productId);

    public void deleteProduct(Integer productId);
}
