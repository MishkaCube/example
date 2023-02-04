package com.example.webhub.services;

import com.example.webhub.entity.Subscribers;
import com.example.webhub.mail.SubscriberCreateDto;
import com.example.webhub.mail.SubscribersDto;
import com.example.webhub.mappers.SubscribersMapper;
import com.example.webhub.repository.SubscribersRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@Data
@RequiredArgsConstructor
public class SubscribersService {

    private final SubscribersMapper mapper;
    private final SubscribersRepository repository;
    private List<String> subscribersList;

    public List<SubscribersDto> getSubs() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::subsToSubsDto).collect(Collectors.toList());
    }

    public List<SubscribersDto> getByEmail(String emailLike) {
        return repository.findByEmail(emailLike).stream()
                .map(mapper::subsToSubsDto).collect(Collectors.toList());
    }

    public SubscribersDto getById(UUID userId) {
        return mapper.subsToSubsDto(repository.findById(userId).orElse(null));
    }

    public void deleteById(UUID userId, SubscriberCreateDto request) {
        Subscribers subscribers = mapper.toSubscribers(request);
        subscribersList.remove(subscribers.getEmail());
        repository.deleteById(userId);
    }

    public SubscribersDto create(SubscriberCreateDto request) {
        log.info("Создаю пользователя");
        Subscribers subscribers = mapper.toSubscribers(request);
        repository.save(subscribers);
        subscribersList.add(subscribers.getEmail());
        return mapper.subsToSubsDto(subscribers);
    }
}
