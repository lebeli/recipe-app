package com.hdmstuttgart.fluffybear.Storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Interface for a storages service which persists multi part files.
 */
public interface StorageService {
	/**
	 * Initialization process of the service
	 */
	void init();

	/**
	 * Persist the file.
	 * @param file file to save
	 * @return the url where the file is stored
	 */
	String store(MultipartFile file) throws StorageServiceException;

	/**
	 * Returns the path to the corresponding name.
	 * @param filename which paths needs to be resolved to path
	 * @return the resolved path of the file
	 * @throws FileNotFoundException if the file does not exists.
	 */
	Path load(String filename) throws FileNotFoundException;

	/**
	 * Creates and returns a resource object from a filename.
	 * @param filename which should be loaded and returned as resource
	 * @return the created resource object
	 * @throws FileNotFoundException if no resources were found for the fiven filename
	 */
	Resource loadAsResource(String filename) throws FileNotFoundException;

	/**
	 * Removes all persistet entries and performs teardown processes.
	 */
	void deleteAll();
}
