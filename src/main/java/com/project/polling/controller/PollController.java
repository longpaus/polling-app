package com.project.polling.controller;

import com.project.polling.dto.*;
import com.project.polling.service.ServerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class PollController {
    private ServerService pollService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/polls")
    public ResponseEntity<PollInfo> createPoll(@RequestBody CreatePollRequest request) {
        return ResponseEntity.ok(pollService.createPoll(request));
    }
    @GetMapping("/polls")
    public ResponseEntity<List<PollInfo>> getAllPolls(){
        return ResponseEntity.ok(pollService.getPolls());
    }

    @GetMapping("polls/{pollId}")
    public ResponseEntity<PollOptions> getPollOptions(@PathVariable String pollId) {
        return ResponseEntity.ok(pollService.getOptions(pollId));
    }

    @PutMapping("/polls/{pollId}/vote")
    public ResponseEntity<PollResult> vote(@PathVariable String pollId, @RequestBody VoteRequest request){
        PollResult result = pollService.vote(pollId, request);
        simpMessagingTemplate.convertAndSend("/topic/poll/"+pollId, result);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


}
