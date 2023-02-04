package com.example.webhub.mappers;

import com.example.webhub.entity.Price;
import com.example.webhub.price.PriceCreateDto;
import com.example.webhub.price.PriceDto;
import com.example.webhub.price.PriceUpdateDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceDto priceToPriceDto(Price entity);

    Price priceUpdateRequestToPriceView(PriceUpdateDto dto, Long id);

    Price toPrice(PriceCreateDto dto);


}
