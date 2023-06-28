package com.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class HashDataDTO {

    private String hashValue;

    private Instant createdDate;
}
