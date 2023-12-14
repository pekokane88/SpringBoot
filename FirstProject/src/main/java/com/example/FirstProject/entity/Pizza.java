package com.example.FirstProject.entity;


import com.example.FirstProject.dto.PizzaDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String price;

    public static Pizza createPizzaEntity(PizzaDto pizzaDto){
        return new Pizza(
                null,
                pizzaDto.getName(),
                pizzaDto.getPrice()
        );
    }

    public void patch(PizzaDto newPizzaDto) {
        if(!Objects.equals(this.id, newPizzaDto.getId())){
            throw new IllegalArgumentException("피자 수정 실패! 잘못된 ID 가 입력되어졌습니다.");
        }
        if(newPizzaDto.getName() != null){
            this.name = newPizzaDto.getName();
        }
        if(newPizzaDto.getPrice() != null){
            this.price = newPizzaDto.getPrice();
        }
    }
}
