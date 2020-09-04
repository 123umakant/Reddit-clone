package com.reddit.clone.configurations;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.*;
import com.reddit.clone.configurations.metadata.AwsS3Credentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.PublicKey;

@Configuration
public class AwsS3Configuration {

    private AwsS3Credentials awsS3Credentials;

    public AwsS3Configuration(AwsS3Credentials awsS3Credentials) {
        this.awsS3Credentials = awsS3Credentials;
    }

    @Bean
    public AmazonS3 getAmazonS3Client() {
        return new AmazonS3Client(new BasicAWSCredentials(awsS3Credentials.AWS_ACCESS_KEY, awsS3Credentials.AWS_SECRET_KEY));
    }
}
