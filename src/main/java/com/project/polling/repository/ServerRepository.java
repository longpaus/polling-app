package com.project.polling.repository;

import com.project.polling.model.Poll;
import com.project.polling.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.util.Arrays.stream;

@Repository
public class ServerRepository {
    private List<Poll> polls;

    public List<Poll> getAllPolls() {
        return this.polls;
    }

    public Optional<Poll> getPollById(String id) {
        return polls.stream()
                .filter(p -> p.getId().equals(id))
                .findAny();
    }

    public Poll addPoll(Poll poll) {
        poll.setId(UUID.randomUUID().toString());
        polls.add(poll);
        return poll;
    }
    /**
     *
     * @param pollId
     * @param result
     */
    public Optional<Poll> savePollResults(String pollId, Map<String, Integer> result) {
        Optional<Poll> optionalPoll = polls.stream()
                .filter(p -> p.getId().equals(pollId))
                .findAny();

        if (optionalPoll.isPresent()) {
            Poll poll = optionalPoll.get();
            poll.setResults(result);
            return Optional.of(poll);
        }
        return Optional.empty();
    }
}
