package com.example.webhub.price;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceUpdateDto {

    private String image;
    private String title;
    private String description;
    private int price;
    private String additional_description;
    private String link;

}
