package com.example.springexample.lowercaseenum;

public enum SampleStatus {
    HELLO("HELLO"),
    WORLD("WORLD");

    private final String status;

    SampleStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
