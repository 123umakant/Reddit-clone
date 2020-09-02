//package com.reddit.clone.configurations;
//
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.s3.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.security.PublicKey;
//
//@Configuration
//public class AwsS3Configuration {
//
//    private String awsAccesskey = "";
//    private String awsSecretKey = "";
//
//    @Bean
//    public AmazonS3 getAmazonS3Client() {
//        return new AmazonS3Client(new BasicAWSCredentials(awsAccesskey, awsSecretKey));
//    }
//}
