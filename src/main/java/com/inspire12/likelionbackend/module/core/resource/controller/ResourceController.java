package com.inspire12.likelionbackend.module.core.resource.controller;


import com.inspire12.likelionbackend.module.core.resource.model.ResourceRequest;
import com.inspire12.likelionbackend.module.core.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/resource")
@RestController
public class ResourceController {

    @GetMapping("/load")
    public String load(String resourceName) throws IOException {

    }

    @PostMapping("/write")
    public String write(@RequestBody ResourceRequest resourceRequest)  {
        throw new UnsupportedOperationException("classpath 형태에선 권장되지 않음");
    }

    @GetMapping("/load/image")
    public ResponseEntity<byte[]> loadImage() throws IOException {

    }
}
