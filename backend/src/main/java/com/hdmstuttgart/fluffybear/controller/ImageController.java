package com.hdmstuttgart.fluffybear.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import com.hdmstuttgart.fluffybear.Storage.StorageFileNotFoundException;
import com.hdmstuttgart.fluffybear.Storage.StorageService;
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


@Controller
public class ImageController {

    private final StorageService storageService;

    @Autowired
    public ImageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping(
            value = "/image/{filename:.+}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return new ResponseEntity<>(file, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/image/add")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        String uuid = storageService.store(file);

        JSONObject response = new JSONObject();
        response.put("url", uuid);

        return response.toString();
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
