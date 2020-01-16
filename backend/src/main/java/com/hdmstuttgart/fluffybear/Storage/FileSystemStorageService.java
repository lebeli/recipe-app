package com.hdmstuttgart.fluffybear.Storage;

import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * Implementation of Storage Service interface for storing Images locally on application server's filesystem.
 */
@Service
public class FileSystemStorageService implements StorageService {

	/**
	 * Path of the root location where the files are stored.
	 */
	private final Path rootLocation;

	/**
	 * Path of the folder where the images are stored.
	 */
	private final Path demoImages = Paths.get("demo_images");

	@Autowired
	public FileSystemStorageService() {
		this.rootLocation = Paths.get("recipe_images");
	}

	/**
	 * Persists the file in the filesystem and returns the public URL to access the file from the spring boot server.
	 * @param file image to save.
	 * @return the public URL of the saved image.
	 * @throws StorageServiceException
	 */
	@Override
	public String store(MultipartFile file) throws StorageServiceException {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		if (file.isEmpty()) {
			throw new StorageServiceException("Failed to store empty file.", StorageErrorCode.MULTIPARTFILE_EMPTY);
		}
		if (filename.contains("..")) {
			// This is a security check
			throw new StorageServiceException("Cannot store file with relative path outside current directory.",
					StorageErrorCode.INVALID_PATH);
		}

		try (InputStream inputStream = file.getInputStream()) {
			String uuid = UUID.randomUUID().toString();
			Files.copy(inputStream, this.rootLocation.resolve(uuid));
			return "localhost/api/images/" + uuid;
		} catch (IOException e) {
			e.printStackTrace();
			throw new StorageServiceException("Error while copying File to directory.");
		}
	}

	/**
	 * Resolves the path from the filename.
	 * @param filename which paths needs to be resolved to path
	 * @return the resolved path of the file
	 */
	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	/**
	 * Creates and returns a resource object from a filename.
	 * @param filename which should be loaded and returned as resource
	 * @return the created resource object
	 * @throws FileNotFoundException if no resources were found for the fiven filename
	 */
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

	/**
	 * Deletes the contents of the root location folder where the files are persisted.
	 */
	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	/**
	 * Create the folder for storing images.
	 */
	@Override
	public void init() {
		// Create upload directory
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}

		copyDemoImages(new File(demoImages.toUri()));
	}

	/**
	 * Copies 30 demo images for demo recipes into the root location folder.
	 * @param folder the folder in which the demo images are stored.
	 */
	public void copyDemoImages(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			try {
				Files.copy(fileEntry.toPath(),
						Paths.get(rootLocation.toString() + "/" + fileEntry.getName()),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
