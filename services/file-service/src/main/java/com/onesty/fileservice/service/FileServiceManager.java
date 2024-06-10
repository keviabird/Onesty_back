package com.onesty.fileservice.service;

import com.onesty.api.core.file.FileService;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceManager implements FileService {

    private final MinioClient client;
    private static final String bucketName = "images";

    @Override
    public String uploadFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        BufferedImage originalImage = null;
        try {
            ensureBucketExists(bucketName);
            originalImage = ImageIO.read(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        BufferedImage image300 = resizeImage(originalImage, 300);
        BufferedImage image200 = resizeImage(originalImage, 200);
        BufferedImage image100 = resizeImage(originalImage, 100);

        String uniqueId = UUID.randomUUID().toString();
        String l = uploadToMinio(image300, uniqueId, "L");
        String m = uploadToMinio(image200, uniqueId, "M");
        String s = uploadToMinio(image100, uniqueId, "S");
        log.info("file with name {} was uploaded with id {}", originalFilename, uniqueId);

        return uniqueId;
    }

    @Override
    public byte[] getFile(String fileId, String size) throws FileNotFoundException {

        String objectName = size + "/" + fileId;

        try (InputStream stream = client.getObject(
                GetObjectArgs.builder().bucket(bucketName).object(objectName).build())) {
            return IOUtils.toByteArray(stream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetSize) {
        return Scalr.resize(originalImage, targetSize);
    }

    private String uploadToMinio(BufferedImage image, String uniqueId, String size) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);

            InputStream inputStream = new ByteArrayInputStream(os.toByteArray());

            String objectName = size + "/" + uniqueId;
            ObjectWriteResponse objectWriteResponse = client.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                                    inputStream, inputStream.available(), -1)
                            .contentType("image/jpeg")
                            .build());
            return objectWriteResponse.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void ensureBucketExists(String bucketName) throws Exception {
        boolean found = client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!found) {
            client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }
}