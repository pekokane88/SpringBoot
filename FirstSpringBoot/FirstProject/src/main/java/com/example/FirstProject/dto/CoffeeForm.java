package com.example.FirstProject.dto;

import com.example.FirstProject.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class CoffeeForm {
    private Long id;
    private String name;
    private String price;

    public Coffee toEntity() { return new Coffee(id, name, price); }
}
