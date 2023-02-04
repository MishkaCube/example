package com.example.webhub.repository;

import com.example.webhub.entity.Price;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends PagingAndSortingRepository<Price, Long> {
    List<Price> findByTitleLike(String title);

}
