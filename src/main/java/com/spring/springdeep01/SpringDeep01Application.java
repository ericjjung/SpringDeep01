package com.spring.springdeep01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ServletComponentScan // @WebServlet 어노테이션이 동작하게 함
@SpringBootApplication
@EnableJpaAuditing
public class SpringDeep01Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringDeep01Application.class, args);
    }
}
