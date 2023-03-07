package com.homey.homeyserver.controller.test;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class StoragePatchTestController {



    @PostMapping("/insert")
    public ResponseEntity insert(@RequestBody MultipartFile image) throws IOException {
        System.out.println(image.toString());
        String uuid = UUID.randomUUID().toString();
        streamObjectUpload(projectId, bucketName, uuid, image);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    @Value("${spring.cloud.gcp.storage.project-id}")
    private String projectId;
    public static void streamObjectUpload(
            String projectId, String bucketName, String objectName, MultipartFile contents) throws IOException {

        String keyFileName = "homey-server-f8393eaad511.json";
        InputStream keyFile = ResourceUtils.getURL("classpath:" + keyFileName).openStream();

        Storage storage = StorageOptions.newBuilder()
                .setProjectId(projectId)
                .setCredentials(GoogleCredentials.fromStream(keyFile))
                .build()
                .getService();

        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(contents.getContentType())
                .build();

        byte[] content = contents.getBytes();

        try (WriteChannel writer = storage.writer(blobInfo)) {
            writer.write(ByteBuffer.wrap(content));
            System.out.println(
                    "Wrote to " + objectName + " in bucket " + bucketName + " using a WriteChannel.");
        }
    }
}
