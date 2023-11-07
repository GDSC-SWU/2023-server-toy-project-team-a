package com.gdsc_teama.servertoyproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
class ServertoyprojectApplication {

    public static void main(String[] args) {
        // 내장 WAS(Web Application Server) 실행. 내장 WAS란 외부에 WAS를 두지 않고 내부에서 WAS를 실행하는 것.
        // 항상 서버에 톰캣을 설치할 필요가 없고 스프링부트로 만들어진 Jar 파일로 실행하면 됨.
        SpringApplication.run(ServertoyprojectApplication.class, args);
    }

}
