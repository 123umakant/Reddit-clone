package com.reddit.clone.service.implementation;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.reddit.clone.configurations.metadata.AwsS3Credentials;
import com.reddit.clone.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileServiceimpl implements FileService {

    @Autowired
    private AwsS3Credentials awsS3Credentials;
    @Autowired
    private AmazonS3 s3Client;

    public FileServiceimpl() {
    }

    public FileServiceimpl(AwsS3Credentials awsS3Credentials, AmazonS3 s3Client) {
        this.awsS3Credentials = awsS3Credentials;
        this.s3Client = s3Client;
    }

    public String upLoadFile(MultipartFile multipartFile) throws IOException {
        String fileName = null;
        try {
            File file = convertMultiPartFileToFile(multipartFile);

            fileName = generateFileName(multipartFile);

            uploadFileToS3Bucket(fileName, file);

            file.delete();
        } catch (AmazonServiceException ase) {
            System.out.println(ase);
        }
        return fileName;
    }

    private void uploadFileToS3Bucket(String filename, File file) {
        s3Client.putObject(new PutObjectRequest(awsS3Credentials.S3_BUCKET_NAME, filename, file).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    private String generateFileName(MultipartFile multipartFile) {
        return multipartFile.getOriginalFilename().replaceAll(" ", "_");
    }

    private File convertMultiPartFileToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(multipartFile.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(convFile);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return convFile;
    }
}
