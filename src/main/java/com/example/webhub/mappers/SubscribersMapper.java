package com.example.webhub.mappers;

import com.example.webhub.entity.Projects;
import com.example.webhub.entity.Subscribers;
import com.example.webhub.mail.SubscriberCreateDto;
import com.example.webhub.mail.SubscribersDto;
import com.example.webhub.mail.SubscribersUpdateDto;
import com.example.webhub.projects.ProjectsCreateDto;
import com.example.webhub.projects.ProjectsDto;
import com.example.webhub.projects.ProjectsUpdateDto;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface SubscribersMapper {

    SubscribersDto subsToSubsDto(Subscribers entity);

    Subscribers subsUpdateRequestToSubsView(SubscribersUpdateDto dto, UUID id);

    Subscribers toSubscribers(SubscriberCreateDto dto);

}
