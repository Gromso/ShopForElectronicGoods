package com.example.ShopForElectronicGoods.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


     private final String URL = "D:\\java_programs\\projekti\\storage\\photos";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/assets/photos/**") // URL prefiks za resurse
                .addResourceLocations("file:"+ URL + "/") // Putanja do direktorijuma sa slikama
                .setCachePeriod(7 * 24 * 3600); // Vreme keširanja u sekundama (ovde je 1 sat)
    }
}
