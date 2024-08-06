package com.project.polling.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class Poll {
    private String id;
    private String name;
    private String description;
    private Map<String, Integer> results;
}
