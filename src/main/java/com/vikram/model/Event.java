package com.vikram.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Event {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String image;

    private String startedAt;

    private String endsAt;

    private String name;

    private Restaurant restaurant;

    private String location;


}
