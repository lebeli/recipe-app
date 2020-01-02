package com.hdmstuttgart.fluffybear.Storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	/**
	 * Persist the file
	 * @param file file to save
	 * @return the url where the file is stored
	 */
	String store(MultipartFile file);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();

}
