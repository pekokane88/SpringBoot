package com.example.FirstProject.service;

import com.example.FirstProject.dto.PizzaDto;
import com.example.FirstProject.entity.Pizza;
import com.example.FirstProject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public List<PizzaDto> index() {
        List<Pizza> pizzas = pizzaRepository.findAll();
        return pizzas.stream().map(PizzaDto::createPizzaDto).collect(Collectors.toList());
    }

    public PizzaDto show(Long id) {
        Pizza pizza = pizzaRepository.findById(id).orElse(null);
        return (pizza != null) ? PizzaDto.createPizzaDto(pizza) : null;
    }

    public PizzaDto create(PizzaDto pizzaDto) {
        Pizza pizza = Pizza.createPizzaEntity(pizzaDto);
        Pizza created = pizzaRepository.save(pizza);
        return PizzaDto.createPizzaDto(created);
    }

    public PizzaDto update(Long id, PizzaDto pizzaDto) {
        Pizza target = pizzaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pizza 조회 실패!! ID 를 다시 확인해주세요."));
        target.patch(pizzaDto);
        Pizza updated = pizzaRepository.save(target);
        return PizzaDto.createPizzaDto(updated);
    }

    public PizzaDto delete(Long id) {
        Pizza target = pizzaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("삭제할 Pizza 조회 실패!! ID 를 다시 확인해주세요"));
        pizzaRepository.deleteById(id);
        return PizzaDto.createPizzaDto(target);
    }
}
