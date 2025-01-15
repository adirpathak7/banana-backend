package com.it.bananachipsbackend.bananachipsbackend.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "druzdz5zn",
                "api_key", "826442283359495",
                "api_secret", "CNg_cAqnlPgH02sGSg_Joork-us"));
    }
}
