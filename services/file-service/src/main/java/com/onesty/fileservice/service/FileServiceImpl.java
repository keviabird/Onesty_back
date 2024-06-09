package com.onesty.fileservice.service;


import com.onesty.api.core.file.FileService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileServiceImpl implements FileService {
    @Override
    public String uploadFile(MultipartFile file) {
        return null;
    }

    @Override
    public MultipartFile getFile(String fileId, String size) {
        return null;
    }
}
