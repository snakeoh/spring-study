package com.example.demo;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Now {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private OffsetDateTime time;

    public Now() {
        time = OffsetDateTime.now();
    }

    public OffsetDateTime getTime() {
        return time;
    }
}
