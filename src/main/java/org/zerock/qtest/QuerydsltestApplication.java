package org.zerock.qtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class QuerydsltestApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuerydsltestApplication.class, args);
    }

}
