package com.booking.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Entity
public class Service {

    @Id
    private Long id;
    private String name;
    private String description;
    private String price;
    private String photo;
    private String additionalPrice;
}
