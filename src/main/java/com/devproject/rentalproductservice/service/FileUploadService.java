package com.devproject.rentalproductservice.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.devproject.rentalproductservice.entity.FileUpload;

public interface FileUploadService {
	void saveFile(MultipartFile file, Long productId);

	List<FileUpload> getAllFiles();
}
