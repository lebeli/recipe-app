package com.hdmstuttgart.fluffybear.controller;

import com.hdmstuttgart.fluffybear.storage.FileSystemStorageService;
import com.hdmstuttgart.fluffybear.storage.StorageService;
import com.hdmstuttgart.fluffybear.storage.StorageServiceException;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;


/**
 * Controller Class for handling image requests and saving/returning recipe images.
 */
@RestController
public class ImageController {
    private final static Logger logger = Logger.getLogger(FileSystemStorageService.class);

    private final StorageService storageService;

    @Autowired
    public ImageController(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * Handler for /images/[filename] resource GET requests. Loads image from database and returs it in the response body.
     *
     * @param filename  name the images has been stored under.
     * @return  response with embedded image and http status code 200.
     */
    @GetMapping(
            value = "/images/{filename:.+}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = null;
        try {
            file = storageService.loadAsResource(filename);
        } catch (FileNotFoundException e) {
            String errorMessage = "Invalid Request for image performed.";
            System.err.println(errorMessage);
            logger.warn(errorMessage + " Filename: " + filename);
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(file, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Handler for /images/add resource POST requests that adds images to the database.
     *
     * @param file  uploaded image recived in a multipart request.
     * @return  response body containing JSON with "url" key and resource url for the presisted image as value.
     */
    @PostMapping("/images/add")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        String imageURL = null;
        try {
            imageURL = storageService.store(file);
        } catch (StorageServiceException e) {
            String warningMessage = "An error occured while storing the image.";
            logger.warn(warningMessage);
            return warningMessage;
        }

        JSONObject response = new JSONObject();
        response.put("url", imageURL);

        return response.toString();
    }

    /**
     * Exception handler in case that requested image is not found in database.
     *
     * @param exc  thrown exception during image retrieval.
     * @return  returns StorageServiceException message.
     */
    @ExceptionHandler(StorageServiceException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageServiceException exc) {
        return ResponseEntity.notFound().build();
    }
}
