package com.gdsc_teama.servertoyproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  // JPA Auditing(데이터의 생성과 수정 일시 추적 및 저장) 어노테이션을 모두 활성화할 수 있도록 함.
@SpringBootApplication
public class ServertoyprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServertoyprojectApplication.class, args);
    }

}