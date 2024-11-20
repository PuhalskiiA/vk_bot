package ru.example.vk_bot_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class VkBotTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(VkBotTestApplication.class, args);
    }

    @Bean
    public RestTemplate initialRestTemplate() {
        return new RestTemplate();
    }

}
