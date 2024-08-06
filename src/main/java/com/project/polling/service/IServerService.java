package com.project.polling.service;

import com.project.polling.dto.*;

import java.util.List;

public interface IServerService {
    List<PollInfo> getPolls();

    PollResult vote(String pollId, VoteRequest request);

    PollOptions getOptions(String pollId);

    PollInfo createPoll(CreatePollRequest request);
}
