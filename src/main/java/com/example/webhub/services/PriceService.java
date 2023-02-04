package com.example.webhub.services;

import com.example.webhub.entity.Price;
import com.example.webhub.mappers.PriceMapper;
import com.example.webhub.price.PriceCreateDto;
import com.example.webhub.price.PriceDto;
import com.example.webhub.price.PriceUpdateDto;
import com.example.webhub.repository.PriceRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class PriceService {

    private final PriceMapper priceMapper;
    private final PriceRepository repository;


    private static final int DEFAULT_PAGE_SIZE = 10;

    public List<PriceDto> getPrices(Integer limit) {
        return repository.findAll(PageRequest.of(0, limit == null ? DEFAULT_PAGE_SIZE : limit)).stream()
                .map(priceMapper::priceToPriceDto).collect(Collectors.toList());
    }

    public List<PriceDto> getByNameLike(String titleLike) {
        return repository.findByTitleLike(titleLike).stream()
                .map(priceMapper::priceToPriceDto).collect(Collectors.toList());
    }

    public PriceDto getById(Long priceId) {
        return priceMapper.priceToPriceDto(repository.findById(priceId).orElse(null));
    }

    public PriceDto update(Long priceId, PriceUpdateDto request) {
        Price price = priceMapper.priceUpdateRequestToPriceView(request, priceId);
        repository.save(price);
        return priceMapper.priceToPriceDto(price);
    }

    public void deleteById(Long priceId) {
        repository.deleteById(priceId);
    }

    public PriceDto create(PriceCreateDto request) {
        log.info("Создаю услугу");
        Price price = priceMapper.toPrice(request);
        repository.save(price);
        return priceMapper.priceToPriceDto(price);
    }

    public List<PriceDto> getPrices() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(priceMapper::priceToPriceDto).collect(Collectors.toList());
    }
}
