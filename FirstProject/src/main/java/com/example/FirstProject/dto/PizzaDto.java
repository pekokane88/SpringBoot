package com.example.FirstProject.dto;

import com.example.FirstProject.entity.Pizza;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PizzaDto {
    private Long id;
    private String name;
    private String price;

    public static PizzaDto createPizzaDto(Pizza pizza) {
        return new PizzaDto(
                pizza.getId(),
                pizza.getName(),
                pizza.getPrice()
        );
    }
}
