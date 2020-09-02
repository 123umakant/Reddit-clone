//package com.reddit.clone.service.implementation;
//
//import com.amazonaws.AmazonServiceException;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import com.reddit.clone.service.FileService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//@Service
//public class FileServiceimpl implements FileService {
//
//    private String bucketName = "null";
//    private String endPointUrl = "null";
//
//    @Autowired
//    private AmazonS3 s3Client;
//
//    public String upLoadFile(MultipartFile multipartFile) throws IOException {
//        String fileUrl = "";
//        try {
//            File file = convertMultiPartFileToFile(multipartFile);
//            String fileName = generateFileName(multipartFile);
//            fileUrl = endPointUrl + "/" + bucketName + "/" + fileName;
//            uploadFileToS3Bucket(fileName, file);
//            file.delete();
//        } catch (AmazonServiceException ase) {
//            System.out.println(ase);
//        }
//        return fileUrl;
//    }
//
//    private void uploadFileToS3Bucket(String filename, File file) {
//        s3Client.putObject(new PutObjectRequest(bucketName, filename, file));
//    }
//
//    private String generateFileName(MultipartFile multipartFile) {
//        return multipartFile.getOriginalFilename().replaceAll(" ", "_");
//    }
//
//    private File convertMultiPartFileToFile(MultipartFile multipartFile) throws IOException {
//        File convFile = new File(multipartFile.getOriginalFilename());
//        FileOutputStream fileOutputStream = new FileOutputStream(convFile);
//        fileOutputStream.write(multipartFile.getBytes());
//        fileOutputStream.close();
//        return convFile;
//    }
//}
