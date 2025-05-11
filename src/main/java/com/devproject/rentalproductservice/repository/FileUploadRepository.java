package com.devproject.rentalproductservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devproject.rentalproductservice.entity.FileUpload;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
	List<FileUpload> findByProductId(int ProductId);

	List<FileUpload> findByProductIdIsNotNull();
}