package com.example.springexample.params;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@SpringBootApplication
public class ListApplication {

    public static void main(String[] args) {
        SpringApplication.run(ListApplication.class, args);
    }

    @GetMapping("/list")
    public List<String> list(@Valid ListRequest listRequest) {
        return listRequest.getLists();
    }

    static class ListRequest {

        List<@NotBlank String> lists;

        public List<String> getLists() {
            return lists;
        }

        public void setLists(List<String> lists) {
            this.lists = lists;
        }
    }
}
