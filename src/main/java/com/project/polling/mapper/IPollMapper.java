package com.project.polling.mapper;

import com.project.polling.dto.CreatePollRequest;
import com.project.polling.dto.PollInfo;
import com.project.polling.dto.PollOptions;
import com.project.polling.dto.PollResult;
import com.project.polling.model.Poll;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
@DecoratedWith(PollMapperDecorator.class)
public interface IPollMapper {
    PollInfo pollToPollInfo(Poll poll);

    PollResult pollToPollResult(Poll poll);

    PollOptions pollToPollOptions(Poll poll);

    Poll createPollRequestToPoll(CreatePollRequest request);
}
