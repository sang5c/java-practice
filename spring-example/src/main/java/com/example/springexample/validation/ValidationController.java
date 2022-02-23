package com.example.springexample.validation;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class ValidationController {

    @GetMapping("/v/{id}")
    public String v(@PathVariable @CustomValid String id) {
        return "hello " + id;
    }
}
