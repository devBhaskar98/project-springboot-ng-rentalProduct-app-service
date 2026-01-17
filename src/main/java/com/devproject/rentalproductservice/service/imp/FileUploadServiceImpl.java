package com.devproject.rentalproductservice.service.imp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.devproject.rentalproductservice.entity.FileUpload;
import com.devproject.rentalproductservice.repository.FileUploadRepository;
import com.devproject.rentalproductservice.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	private final FileUploadRepository fileUploadRepository;
	private final Path rootLocation = Paths.get("uploads");

	@Autowired
	public FileUploadServiceImpl(FileUploadRepository fileUploadRepository) {
		this.fileUploadRepository = fileUploadRepository;
	}

	@Override
	public void saveFile(MultipartFile file, Long productId) {
		try {
			if (!Files.exists(rootLocation)) {
				Files.createDirectories(rootLocation);
			}
			Path destinationFile = rootLocation.resolve(Paths.get(file.getOriginalFilename())).normalize();
			Files.copy(file.getInputStream(), destinationFile);

			FileUpload fileUploadEntity = new FileUpload(file.getOriginalFilename(), destinationFile.toString(),
					productId);
			fileUploadRepository.save(fileUploadEntity);
		} catch (IOException e) {
			throw new RuntimeException("Failed to store file", e);
		}
	}

	@Override
	public List<FileUpload> getAllFiles() {
		return fileUploadRepository.findAll();
	}
}
