package com.devproject.rentalproductservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.devproject.rentalproductservice.common.dto.PageRequestDTO;
import com.devproject.rentalproductservice.entity.Product;
import com.devproject.rentalproductservice.service.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin(
        origins = "http://localhost:4200",
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST }
)
public class ProductController {
	
	@Autowired
    private ProductService productService;
	
	
	//add product
    @PostMapping("/")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product products = this.productService.addProduct(product);
        return ResponseEntity.ok(products);
    }
    
    @PostMapping("/page")
	public Page<Product> getAllProductPaginatedResult(@RequestBody PageRequestDTO dto){
		
		Pageable pageable = new PageRequestDTO().getPageable(dto);
		Page<Product> productPage = productService.findAllByPagination(pageable);		
		
		return productPage;
	
	}
    
  //get product
    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable("productId") Integer productId) {
        return this.productService.getProduct(productId);
    }

//	//get all product
    @GetMapping("/")
    public ResponseEntity<?> getAllProduct() {
        return ResponseEntity.ok(this.productService.getProduct());
    }
	
    
    
    //update product
    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
        return this.productService.updateProduct(product);
    }

    //delete product
    @DeleteMapping("/{productId}")
    public void deleteCategory(@PathVariable("productId") Integer productId) {
        this.productService.deleteProduct(productId);
    }
}
