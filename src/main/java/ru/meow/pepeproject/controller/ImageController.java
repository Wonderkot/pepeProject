package ru.meow.pepeproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.meow.pepeproject.service.FileEntityService;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {

    private final FileEntityService fileEntityService;

    @GetMapping(value = "getPepe")
    public ResponseEntity<byte[]> getPepeImage() {
        try {
            byte[] res = fileEntityService.getRandomPepe();
            HttpHeaders headers = new HttpHeaders();
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            headers.setContentType(MediaType.IMAGE_PNG);

            return new ResponseEntity<>(res, headers, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().body(e.getMessage().getBytes(StandardCharsets.UTF_8));
        }
    }
}
