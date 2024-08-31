package com.devproject.rentalproductservice.service;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.devproject.rentalproductservice.entity.Product;

public interface ProductService {
	
	public Product addProduct(Product product);
	
	public Product updateProduct(Product product);
	
	public Set<Product> getProduct();
	
	public Product getProduct(Integer productId);

    public void deleteProduct(Integer productId);
    
    Page<Product> findAllByPagination(Pageable pageable);
}
