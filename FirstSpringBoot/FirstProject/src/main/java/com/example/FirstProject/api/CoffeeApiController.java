package com.example.FirstProject.api;

import com.example.FirstProject.dto.CoffeeForm;
import com.example.FirstProject.entity.Coffee;
import com.example.FirstProject.repository.CoffeeRepository;
import com.example.FirstProject.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/coffeeApi")
public class CoffeeApiController {

    @Autowired
    private CoffeeService coffeeService;

    //GET
    @GetMapping("/coffees")
    public List<Coffee> index(){
        return coffeeService.index();
    }
    @GetMapping("/coffees/{id}")
    public ResponseEntity<Coffee> show(@PathVariable Long id){
        Coffee coffee = coffeeService.show(id);
        if(coffee == null){
            log.info("Bad Request Id No: {}", id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(coffee);
    }
    //POST
    @PostMapping("/coffees")
    public @ResponseBody ResponseEntity<Coffee> create(@RequestBody CoffeeForm dto){
        // 1. Dto 2 Entity
        Coffee newCoffee = coffeeService.create(dto);
        // 2. Save the data
        return (newCoffee != null) ? ResponseEntity.status(HttpStatus.OK).body(newCoffee) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    //PATCH
    @PatchMapping("/coffees/{id}")
    public @ResponseBody ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm dto){
        Coffee updated = coffeeService.update(id, dto);
        return (updated != null) ? ResponseEntity.status(HttpStatus.OK).body(updated) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto.toEntity());
    }
    //DELETE
    @DeleteMapping("/coffees/{id}")
    public @ResponseBody ResponseEntity<Coffee> delete(@PathVariable Long id){
        Coffee deleted = coffeeService.delete(id);
        //NO_CONTENT -> 요청 내용이 잘 수행 되었고, 딱히 return 할 내용이 없을 때
        //OK -> 요청 내용이 잘 수행 되었고, return 할 내용이 있을 때
        return (deleted != null) ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
