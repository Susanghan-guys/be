package com.susanghan_guys.server.global.s3.application;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    public void uploadFile(String bucketName, String key, byte[] fileBytes, String contentType) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(fileBytes.length);
        metadata.setContentType(contentType);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes);
        s3Client.putObject(bucketName, key, inputStream, metadata);
    }

    public String uploadFile(MultipartFile file, String dir) {
        String fileName = dir + "/" + UUID.randomUUID();
        String contentType = file.getContentType();

        try {
            byte[] bytes = file.getBytes();
            uploadFile(bucket, fileName, bytes, contentType);
            return generateUrl(fileName);
        } catch (Exception e) {
            throw new WorkException(WorkErrorCode.FILE_UPLOAD_FAILED);
        }
    }

    private String generateUrl(String key) {
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucket, region, key);
    }
}