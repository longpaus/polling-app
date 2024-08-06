package com.project.polling.service;

import com.project.polling.dto.*;
import com.project.polling.mapper.IPollMapper;
import com.project.polling.model.Poll;
import com.project.polling.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ServerService implements IServerService{

    private final IPollMapper pollMapper;
    private final ServerRepository repository;

    @Override
    public List<PollInfo> getPolls() {
        return repository.getAllPolls()
                .stream()
                .map(pollMapper::pollToPollInfo)
                .toList();
    }

    @Override
    public PollResult vote(String pollId, VoteRequest request) {
        Poll poll = repository.getPollById(pollId)
                .orElseThrow(() -> new RuntimeException("invalid pollId"));
        Map<String,Integer> results = poll.getResults();
        String selectedOption = request.getOptionSelected();
        if(!results.containsKey(selectedOption)) {
            throw new RuntimeException("option does not exist in poll");
        }
        results.compute(selectedOption, (k, numVotes) -> numVotes + 1);
        Poll newPoll = repository.savePollResults(pollId, results)
                .orElseThrow(() -> new RuntimeException("invalid pollId"));
        return pollMapper.pollToPollResult(newPoll);
    }

    @Override
    public PollOptions getOptions(String pollId) {
        Poll poll = repository.getPollById(pollId)
                .orElseThrow(() -> new RuntimeException("invalid pollId"));
        return pollMapper.pollToPollOptions(poll);
    }

    @Override
    public PollInfo createPoll(CreatePollRequest request) {
        List<String> options = request.getOptions();
        Set<String> set = new HashSet<>(options);
        if(set.size() < options.size()) {
            throw new RuntimeException("There can't be duplicate options in poll");
        }
        Poll poll = repository.addPoll(pollMapper.createPollRequestToPoll(request));
        return pollMapper.pollToPollInfo(poll);
    }

}
