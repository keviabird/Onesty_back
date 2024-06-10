package com.onesty.api.core.file;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface FileService {

    @PostMapping("/files")
    String uploadFile(@RequestParam("file") MultipartFile file);

    @GetMapping("/files/{fileId}")
    byte[] getFile(@PathVariable("fileId") String fileId,
                          @RequestParam String size) throws FileNotFoundException;
}
