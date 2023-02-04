package com.example.webhub.price;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PriceDto {

    private Long id;
    private String image;
    private String title;
    private String description;
    private int price;
    private String additional_description;
    private String link;


}
