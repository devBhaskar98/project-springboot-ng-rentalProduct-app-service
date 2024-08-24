package com.devproject.rentalproductservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devproject.rentalproductservice.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
