package com.example.springexample.jackson;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class JacksonController {

    @GetMapping("/ss")
    public String ss() {
        return "ss";
    }

    @GetMapping("/j")
    public JacksonResponse jackson() {
        return new JacksonResponse("테스트");
    }

    @Getter
    static class JacksonResponse {

        private String name;
        private int age;
        private LocalDateTime localDateTime;

        public JacksonResponse(String name) {
            this.name = name;
        }
    }
}
