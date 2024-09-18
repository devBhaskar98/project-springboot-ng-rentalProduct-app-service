package com.devproject.rentalproductservice.service.imp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.devproject.rentalproductservice.entity.Product;
import com.devproject.rentalproductservice.repository.ProductRepository;
import com.devproject.rentalproductservice.service.imp.ProductServiceImpl;

class ProductServiceImplTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductServiceImpl productService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Initialize mocks
	}

	@Test
	void testAddProduct() {
		Product product = new Product();
		product.setId(1);
		product.setName("Test Product");

		when(productRepository.save(any(Product.class))).thenReturn(product);

		Product savedProduct = productService.addProduct(product);

		assertNotNull(savedProduct);
		assertEquals(1, savedProduct.getId());
		assertEquals("Test Product", savedProduct.getName());
	}

	@Test
	void testGetProductById() {
		Product product = new Product();
		product.setId(1);
		product.setName("Test Product");

		when(productRepository.findById(1)).thenReturn(Optional.of(product));

		Product foundProduct = productService.getProduct(1);

		assertNotNull(foundProduct);
		assertEquals(1, foundProduct.getId());
	}

	@Test
	void testGetAllProducts() {
		Product product1 = new Product();
		product1.setId(1);
		product1.setName("Test Product 1");

		Product product2 = new Product();
		product2.setId(2);
		product2.setName("Test Product 2");

		when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

		Set<Product> products = productService.getProduct();

		assertNotNull(products);
		assertEquals(2, products.size());
	}

	@Test
	void testUpdateProduct() {
		Product product = new Product();
		product.setId(1);
		product.setName("Updated Product");

		when(productRepository.save(any(Product.class))).thenReturn(product);

		Product updatedProduct = productService.updateProduct(product);

		assertNotNull(updatedProduct);
		assertEquals("Updated Product", updatedProduct.getName());
	}

	@Test
	void testDeleteProduct() {
		Product product = new Product();
		product.setId(1);

		doNothing().when(productRepository).delete(any(Product.class));

		productService.deleteProduct(1);

		verify(productRepository, times(1)).delete(any(Product.class));
	}

	@Test
	void testFindAllByPagination() {
		Product product = new Product();
		product.setId(1);
		product.setName("Test Product");

		Pageable pageable = PageRequest.of(0, 10);
		Page<Product> productPage = new PageImpl<>(Arrays.asList(product), pageable, 1);

		when(productRepository.findAll(pageable)).thenReturn(productPage);

		Page<Product> result = productService.findAllByPagination(pageable);

		assertNotNull(result);
		assertEquals(1, result.getTotalElements());
	}
}
