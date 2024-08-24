package com.devproject.rentalproductservice.service.imp;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devproject.rentalproductservice.entity.Product;
import com.devproject.rentalproductservice.repository.ProductRepository;
import com.devproject.rentalproductservice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
    private ProductRepository productRepository;
	
	@Override
    public Product addProduct(Product product) {
        return this.productRepository.save(product);
    }
	
	@Override
	public Set<Product> getProduct() {
		return new LinkedHashSet<>(this.productRepository.findAll());
	}

}
