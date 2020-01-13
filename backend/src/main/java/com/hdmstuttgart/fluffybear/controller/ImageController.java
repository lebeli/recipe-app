package com.hdmstuttgart.fluffybear.controller;

import com.hdmstuttgart.fluffybear.Storage.StorageService;
import com.hdmstuttgart.fluffybear.Storage.StorageServiceException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;


@Controller
public class ImageController {

    private final StorageService storageService;

    @Autowired
    public ImageController(StorageService storageService) {
        this.storageService = storageService;
    }

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
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(file, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/images/add")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        String imageURL = null;
        try {
            imageURL = storageService.store(file);
        } catch (StorageServiceException e) {
            System.err.println("An error occured while storing image.");
            return "An error occured while storing image.";
        }

        JSONObject response = new JSONObject();
        response.put("url", imageURL);

        return response.toString();
    }

    @ExceptionHandler(StorageServiceException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageServiceException exc) {
        return ResponseEntity.notFound().build();
    }
}
