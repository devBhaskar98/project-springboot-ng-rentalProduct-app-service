package com.devproject.rentalproductservice.service.imp;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devproject.rentalproductservice.entity.FileUpload;
import com.devproject.rentalproductservice.entity.Product;
import com.devproject.rentalproductservice.repository.FileUploadRepository;
import com.devproject.rentalproductservice.repository.ProductRepository;
import com.devproject.rentalproductservice.service.ProductService;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private FileUploadRepository fileUploadRepository;

	@Override
	public Product addProduct(Product product) {
		return this.productRepository.save(product);
	}

	@Override
	public Set<Product> getProduct() {

		List<Product> products = productRepository.findAll();
		for (Product product : products) {
			List<FileUpload> files = fileUploadRepository.findByProductId(product.getId());
			product.setFiles(new LinkedHashSet<>(files));
		}
		return new LinkedHashSet<>(products);
	}

	@Override
	public Product updateProduct(Product product) {
		return this.productRepository.save(product);
	}

	@Override
	@Transactional
	public Product getProduct(Integer productId) {
		Product product = productRepository.findById(productId).get();
		List<FileUpload> files = fileUploadRepository.findByProductId(product.getId());
		product.setFiles(new LinkedHashSet<>(files));
		return this.productRepository.findById(productId).get();
	}

	@Override
	public Product deleteProduct(Integer productId) {
		Optional<Product> optionalProduct = productRepository.findById(productId);

		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			productRepository.delete(product);
			return optionalProduct.get();
		} else {
			Product notFoundProduct = new Product();
			notFoundProduct.setId(0); // Indicate "not found"
			return notFoundProduct;
		}
	}

	@Override
	public Page<Product> findAllByPagination(Pageable pageable) {

		return this.productRepository.findAll(pageable);
	}

}
