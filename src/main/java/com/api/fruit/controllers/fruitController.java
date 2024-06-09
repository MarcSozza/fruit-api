package com.api.fruit.controllers;

import com.api.fruit.models.Fruit;
import com.api.fruit.services.FruitService;
import com.api.fruit.utils.ValidatorsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class fruitController {

    @Autowired
    public FruitService fruitService;

    @GetMapping("/fruits")
    public List<Fruit> getFruits() {
        return fruitService.getAllFruits();
    }

    @PostMapping("/fruit")
    public Fruit addFruit(@RequestBody Fruit fruit) {
        ValidatorsUtils.checkIdNotPresent(fruit);
        return fruitService.addFruit(fruit);
    }

    @PutMapping("/fruit/{id}")
    public Fruit updateFruit(@PathVariable String id, @RequestBody Fruit fruit) {
        ValidatorsUtils.checkIdNotPresent(fruit);
        return fruitService.updateFruit(id, fruit);
    }
}
