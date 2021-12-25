package com.example.jojolduspringbootaws.web;

import com.example.jojolduspringbootaws.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public HelloResponseDto hello(@RequestParam String name, @RequestParam int amount) {
        return new HelloResponseDto(name, amount);
    }

}
