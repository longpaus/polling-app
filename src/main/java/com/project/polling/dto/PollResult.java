package com.project.polling.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class PollResult {
    Map<String, Integer> results;
}
