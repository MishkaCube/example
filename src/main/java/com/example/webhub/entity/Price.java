package com.example.webhub.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    @Column(columnDefinition = "text")
    @Size(max = 50000)
    private String image;
    private String title;
    private String description;
    private int price;
    private String additional_description;
    private String link;
}
