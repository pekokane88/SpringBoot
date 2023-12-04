package com.example.FirstProject.api;

import com.example.FirstProject.dto.CoffeeForm;
import com.example.FirstProject.entity.Coffee;
import com.example.FirstProject.repository.CoffeeRepository;
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
    private CoffeeRepository coffeeRepository;

    //GET
    @GetMapping("/coffees")
    public List<Coffee> index(){
        return coffeeRepository.findAll();
    }
    @GetMapping("/coffees/{id}")
    public ResponseEntity<Coffee> show(@PathVariable Long id){
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        if(coffee == null){
            log.info("Bad Request Id No: {}", id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(coffee);
    }
    //POST
    @PostMapping("/coffees")
    public @ResponseBody ResponseEntity<Coffee> create(@RequestBody CoffeeForm dto){
        // 1. Dto 2 Entity
        Coffee newCoffee = dto.toEntity();
        // 2. Save the data
        return ResponseEntity.status(HttpStatus.OK).body(coffeeRepository.save(newCoffee));
    }
    //PATCH
    @PatchMapping("/coffees/{id}")
    public @ResponseBody ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm dto){
        // 1. Get New Data from dto
        Coffee newCoffee = dto.toEntity();
        // 2. Find original Entity
        Coffee originCoffee = coffeeRepository.findById(id).orElse(null);
        if (originCoffee == null){
            log.info("Bad Request ! ID : {}, Coffee : {}", id, newCoffee.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 3. move to data.
        originCoffee.patch(newCoffee);
        // 4. update the data
        Coffee updated = coffeeRepository.save(originCoffee);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    //DELETE
    @DeleteMapping("/coffees/{id}")
    public @ResponseBody ResponseEntity<Coffee> delete(@PathVariable Long id){
        // 1. Get delete data.
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if (target == null){
            log.info("BAD REQUEST ID : {}", id);
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //2. delete the data
        coffeeRepository.delete(target);
        //3. return success
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
