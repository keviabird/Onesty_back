package com.onesty.fileservice.service;


import com.onesty.api.core.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@RestController
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileServiceManager manager;
    @Override
    public String uploadFile(MultipartFile file) {
        return manager.uploadFile(file);
    }

    @Override
    public byte[] getFile(String fileId, String size) throws FileNotFoundException {
        return manager.getFile(fileId, size);
    }
}
