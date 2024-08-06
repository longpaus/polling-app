package com.project.polling.mapper;

import com.project.polling.dto.CreatePollRequest;
import com.project.polling.dto.PollInfo;
import com.project.polling.dto.PollOptions;
import com.project.polling.dto.PollResult;
import com.project.polling.model.Poll;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public abstract class PollMapperDecorator implements IPollMapper{

    @Autowired
    @Qualifier("delegate")
    private IPollMapper delegate;

    @Override
    public PollInfo pollToPollInfo(Poll poll) {
        return null;
    }

    @Override
    public PollResult pollToPollResult(Poll poll) {
        return delegate.pollToPollResult(poll);
    }

    @Override
    public PollOptions pollToPollOptions(Poll poll) {
        PollOptions options = delegate.pollToPollOptions(poll);
        options.setOptions(new ArrayList<>(poll.getResults().keySet()));
        return options;
    }

    @Override
    public Poll createPollRequestToPoll(CreatePollRequest request) {
        Poll poll = delegate.createPollRequestToPoll(request);
        List<String> options = request.getOptions();
        Map<String, Integer> results = new ConcurrentHashMap<>();
        for(String option: options) {
            results.put(option, 0);
        }
        poll.setResults(results);
        return poll;
    }
}
