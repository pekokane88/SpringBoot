package com.example.FirstProject.api;

import com.example.FirstProject.dto.PizzaDto;
import com.example.FirstProject.entity.Pizza;
import com.example.FirstProject.service.PizzaService;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/pizzaApi")
public class PizzaApiController {

    @Autowired
    private PizzaService pizzaService;

    //Pizza 데이터 전체 조회
    @GetMapping("/index")
    public ResponseEntity<List<PizzaDto>> index(){
        List<PizzaDto> pizzaDtos = pizzaService.index();
        //NO CONTENT(204) 는 FAIL 의 영역은 아님. -> 조회하였는데 데이터가 없었을 뿐.
        return (pizzaDtos != null) ? ResponseEntity.status(HttpStatus.OK).body(pizzaDtos) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    //Pizza 데이터 단일 조회
    @GetMapping("/show/{id}")
    public ResponseEntity<PizzaDto> show(@PathVariable Long id){
        PizzaDto pizzaDto = pizzaService.show(id);
        //NO CONTENT(204) 는 FAIL 의 영역은 아님. -> 조회하였는데 데이터가 없었을 뿐.
        return (pizzaDto != null) ? ResponseEntity.status(HttpStatus.OK).body(pizzaDto) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    //Pizza 데이터 생성
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PizzaDto> create(@RequestBody PizzaDto pizzaDto){
        PizzaDto created = pizzaService.create(pizzaDto);
        return (created != null) ? ResponseEntity.status(HttpStatus.OK).body(created) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pizzaDto);
    }

    //Pizza 데이터 수정
    @PatchMapping("/update/{id}")
    public ResponseEntity<PizzaDto> update(@PathVariable Long id, @RequestBody PizzaDto pizzaDto){
        if(!Objects.equals(id, pizzaDto.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        PizzaDto updated = pizzaService.update(id, pizzaDto);
        return (updated  != null) ? ResponseEntity.status(HttpStatus.OK).body(updated) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pizzaDto);
    }
    
    //Pizza 데이터 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PizzaDto> delete(@PathVariable Long id){
        PizzaDto deleted = pizzaService.delete(id);
        return (deleted != null) ? ResponseEntity.status(HttpStatus.OK).body(deleted) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
