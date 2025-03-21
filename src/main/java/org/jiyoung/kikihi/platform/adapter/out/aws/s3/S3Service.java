//package org.jiyoung.kikihi.infrastructure.storage;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import software.amazon.awssdk.core.sync.RequestBody;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.model.PutObjectRequest;
//
//import java.io.IOException;
//import java.nio.file.Path;
//import java.util.UUID;
//
//@Service
//public class S3Service {
//    private final AmazonS3 amazonS3;
//
//    @Value("${aws_s3_bucket_name_goods}")
//    private String bucketName;
//
//    // 파일 업로드
//    public String uploadFile(MultipartFile file) throws IOException{
//        S3Client s3Client= S3Client.builder().build();
//        String fileName=generateUniqueFileName(file.getOriginalFilename());
//        s3Client.putObject(PutObjectRequest.builder().bucket(bucketName).key(fileName).build(),
//                RequestBody.fromInputStream(file.getInputStream(),file.getSize()));
//        return fileName;
//    }
//
//    // 파일 이름 생성
//    private String generateUniqueFileName(String originalFilename) {
//        String extension=originalFilename.substring(originalFilename.lastIndexOf("."));
//        return "origin/"+UUID.randomUUID().toString()+extension;
//    }
//
//}
