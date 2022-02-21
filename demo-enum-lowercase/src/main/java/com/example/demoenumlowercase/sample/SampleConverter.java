package com.example.demoenumlowercase.sample;

import org.springframework.core.convert.converter.Converter;

public class SampleConverter implements Converter<String, SampleStatus> {

    @Override
    public SampleStatus convert(String value) {
        return SampleStatus.valueOf(value.toUpperCase());
    }
}
