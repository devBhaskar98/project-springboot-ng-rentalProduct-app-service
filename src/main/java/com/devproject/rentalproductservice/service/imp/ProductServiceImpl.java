package com.devproject.rentalproductservice.service.imp;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Override
	public Product updateProduct(Product product) {
		return this.productRepository.save(product);
	}

	@Override
	public Product getProduct(Integer productId) {
		return this.productRepository.findById(productId).get();
	}

	@Override
	public void deleteProduct(Integer productId) {
		Product product = new Product();
		product.setId(productId);
        this.productRepository.delete(product);
		
	}

	@Override
	public Page<Product> findAllByPagination(Pageable pageable) {
		
		return this.productRepository.findAll(pageable);
	}

}
