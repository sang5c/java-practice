package com.example.demoenumlowercase.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hello/{sampleStatus}")
    public String hello(@PathVariable SampleStatus sampleStatus) {
        return sampleStatus.getStatus();
    }

    @GetMapping("/hello")
    public String hello2(@RequestParam SampleStatus sampleStatus) {
        return sampleStatus.getStatus();
    }
}
