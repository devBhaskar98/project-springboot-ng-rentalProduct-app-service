package com.devproject.rentalproductservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.devproject.rentalproductservice.entity.Product;
import com.devproject.rentalproductservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

class ProductControllerTest {

	private MockMvc mockMvc;

	@Mock
	private ProductService productService;

	@InjectMocks
	private ProductController productController;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Use openMocks instead of initMocks
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
		objectMapper = new ObjectMapper();
	}

	@Test
	void testAddProduct() throws Exception {
		Product product = new Product();
		product.setId(1);
		product.setName("Test Product");

		when(productService.addProduct(any(Product.class))).thenReturn(product);

		mockMvc.perform(post("/product/").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product))).andExpect(status().isOk());
	}

	@Test
	void testGetProduct() throws Exception {
		Product product = new Product();
		product.setId(1);
		product.setName("Test Product");

		when(productService.getProduct(1)).thenReturn(product);

		// Perform the request and capture the result
		String response = mockMvc.perform(get("/product/1")).andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString();

		// Log the response
		System.out.println("Response: " + response);

		mockMvc.perform(get("/product/1")).andExpect(status().isOk());
	}

	@Test
	@Disabled
	void testGetAllProductsPaginated() throws Exception {
		Product product = new Product();
		product.setId(1);
		product.setName("Test Product");

		PageRequest pageRequest = PageRequest.of(0, 5);
		Page<Product> productPage = new PageImpl<>(Collections.singletonList(product), pageRequest, 1);

		when(productService.findAllByPagination(any(PageRequest.class))).thenReturn(productPage);

		mockMvc.perform(post("/product/page").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(pageRequest))).andExpect(status().isOk());
	}

	@Test
	void testUpdateProduct() throws Exception {
		Product product = new Product();
		product.setId(1);
		product.setName("Updated Product");

		when(productService.updateProduct(any(Product.class))).thenReturn(product);

		mockMvc.perform(put("/product/update").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product))).andExpect(status().isOk());
	}

	@Test
	@Disabled
	void testDeleteProduct() throws Exception {
		doNothing().when(productService).deleteProduct(1);

		mockMvc.perform(delete("/product/1")).andExpect(status().isOk());
	}
}
