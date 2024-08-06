package com.project.polling.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreatePollRequest {
    private String name;
    private String description;
    private List<String> options;
}
