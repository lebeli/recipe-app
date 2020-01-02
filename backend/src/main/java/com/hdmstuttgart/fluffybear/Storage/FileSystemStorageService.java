package com.hdmstuttgart.fluffybear.Storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService() {
		this.rootLocation = Paths.get("upload-dir");
	}

	@Override
	public String store(MultipartFile file) throws StorageServiceException {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		if (file.isEmpty()) {
			throw new StorageServiceException("Failed to store empty file.");
		}
		if (filename.contains("..")) {
			// This is a security check
			throw new StorageServiceException("Cannot store file with relative path outside current directory.");
		}

		try (InputStream inputStream = file.getInputStream()) {
			String uuid = UUID.randomUUID().toString();
			Files.copy(inputStream, this.rootLocation.resolve(uuid));
			return "localhost:8080/image/" + uuid;
		} catch (IOException e) {
			e.printStackTrace();
			throw new StorageServiceException("Error while copying File to directory.");
		}
	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) throws FileNotFoundException {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new FileNotFoundException(
						"Could not read file: " + filename);
			}
		} catch (MalformedURLException e) {
			throw new FileNotFoundException("Could not read file: " + filename);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		// Create upload directory
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Copy demo images from resources to upload directory
		try {
			File[] files = new ClassPathResource("/demo_images").getFile().listFiles();
			copyFiles(files);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void copyFiles(File[] files) {
		for (File f : files) {
			try {
				Files.copy(f.toPath(),
						Paths.get(rootLocation.toString() + "/" + f.getName()),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
