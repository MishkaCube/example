package com.example.webhub.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subscribers {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String email;

}
