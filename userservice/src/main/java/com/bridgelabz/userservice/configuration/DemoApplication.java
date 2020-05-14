package com.bridgelabz.userservice.configuration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication
{
  
   @Bean
   public RestTemplate getRestTemplate() {
      return new RestTemplate();
   }
}
