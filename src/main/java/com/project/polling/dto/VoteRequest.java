package com.project.polling.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoteRequest {
    private String optionSelected;
}
