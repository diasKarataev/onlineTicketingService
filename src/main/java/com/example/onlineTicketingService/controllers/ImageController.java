package com.example.onlineTicketingService.controllers;

import com.example.onlineTicketingService.models.Image;
import com.example.onlineTicketingService.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping(path = "api/images")
public class ImageController {
    @Autowired
    public ImageRepository imageRepository;

    @GetMapping("/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id){
        Image image = imageRepository.findById(id).orElseThrow();
        return ResponseEntity.ok()
            .header("fileName", image.getOriginalFileName())
            .contentType(MediaType.valueOf(image.getContentType()))
            .contentLength((image.getSize()))
            .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}
