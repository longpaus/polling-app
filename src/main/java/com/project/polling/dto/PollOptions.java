package com.project.polling.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PollOptions {
    private List<String> options;
}
