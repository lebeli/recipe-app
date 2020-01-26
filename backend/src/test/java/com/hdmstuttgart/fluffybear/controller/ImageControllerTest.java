package com.hdmstuttgart.fluffybear.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ImageController.class)
public class ImageControllerTest extends ControllerTest {
    @Autowired
    protected MockMvc httpRequest;
    MockMultipartFile imagePart1 = new MockMultipartFile("file", "filename.jpg", "image/jpeg", "image".getBytes());
    MockMultipartFile imagePart2 = new MockMultipartFile("file", "filename.jpg", "image/jpeg", "image".getBytes());

    @Test
    public void imagesRequestRecieved() throws Exception {
        httpRequest.perform(get("/images/image.jpg"))
                .andExpect(status().isOk());
    }

    @Test
    public void imagesRequestAddRecieved() throws Exception {
        httpRequest.perform(MockMvcRequestBuilders.multipart("/images/add")
                .file(imagePart1)
                .file(imagePart2))
                .andExpect(status().isOk());
    }
}
