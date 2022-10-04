package com.valleon.rewardyourteacherapi.infrastructure.configuration.security;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Value("${cloudinary_name:cloudName}")
    private String cloudName;
    @Value("${cloudinary_api_key:apiKey}")
    private String apiKey;
    @Value("${cloudinary_api_secret:apiSecret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary(){
        Cloudinary cloudinary;
        Map<String, Object> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        config.put("secure", true);
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }
}
