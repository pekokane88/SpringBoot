package com.example.FirstProject.service;

import com.example.FirstProject.dto.CoffeeForm;
import com.example.FirstProject.entity.Coffee;
import com.example.FirstProject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<Coffee> index() {
        return  coffeeRepository.findAll();
    }

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeForm dto) {
        try{
            Coffee newCoffee = dto.toEntity();
            return coffeeRepository.save(newCoffee);
        }
        catch(Exception exception) {
            log.info(exception.toString());
            return null;
        }
    }

    public Coffee update(Long id, CoffeeForm dto) {
        try{
            // 1. Get New Data from dto
            Coffee newCoffee = dto.toEntity();
            // 2. Find original Entity
            Coffee originCoffee = coffeeRepository.findById(id).orElse(null);
            if (originCoffee == null){
                log.info("Bad Request ! ID : {}, Coffee : {}", id, newCoffee.toString());
                //There is no coffee data.
                return null;
            }
            // 3. move to data.
            originCoffee.patch(newCoffee);
            // 4. update the data
            return coffeeRepository.save(originCoffee);
        }
        catch(Exception exception){
            log.info(exception.toString());
            return null;
        }
    }

    public Coffee delete(Long id) {
        try {
            // 1. Get delete data.
            Coffee target = coffeeRepository.findById(id).orElse(null);
            if (target == null){
                log.info("BAD REQUEST ID : {}", id);
                return null;
            }
            //2. delete the data
            coffeeRepository.delete(target);
            //3. return success
            return target;
        }
        catch (Exception exception){
            log.info(exception.toString());
            return null;
        }
    }
}
