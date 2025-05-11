package com.devproject.rentalproductservice.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.devproject.rentalproductservice.Exception.StorageFileNotFoundException;
import com.devproject.rentalproductservice.service.FileUploadService;
import com.devproject.rentalproductservice.service.StorageService;

@RestController
@RequestMapping("/api/fileupload/v1")
public class FileUploadController {

	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@Autowired
	private FileUploadService fileUploadSvc;

	@GetMapping("/files")
	public ResponseEntity<List<String>> listUploadedFiles() throws IOException {
		List<String> fileUrls = storageService.loadAll()
				.map(path -> "/api/fileupload/v1/files/" + path.getFileName().toString()).collect(Collectors.toList());

		return ResponseEntity.ok(fileUrls);
	}

	@GetMapping("/files/{filename:.+}")
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);

		if (file == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "productId", required = false) Long productId) {

		System.out.println("my product id is " + productId);
		storageService.store(file);

		// store it in uploaded_files table
		fileUploadSvc.saveFile(file, productId);

		return ResponseEntity.ok("You successfully uploaded " + file.getOriginalFilename() + "!");
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
}