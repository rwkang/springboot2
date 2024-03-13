package shop.onekorea.springboot2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public String init() {

        return "Annotation @Configuration 테스트";

    }

}
