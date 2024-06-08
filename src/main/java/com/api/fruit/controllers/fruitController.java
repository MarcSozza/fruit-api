package com.api.fruit.controllers;

import com.api.fruit.models.Fruit;
import com.api.fruit.repositories.FruitRepository;
import com.api.fruit.services.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class fruitController {

    @Autowired
    public FruitService fruitService;

    @GetMapping("/fruits")
    public List<Fruit> getFruits() {
        return fruitService.getAllFruits();
    }

    @PostMapping("/fruits")
    public Fruit addFruit(@RequestBody Fruit fruit) {
        return fruitService.addFruit(fruit);
    }

    @PutMapping("/fruits/{id}")
    public Fruit updateFruit(@PathVariable int id, @RequestBody Fruit fruit) {
        return fruitService.updateFruit(id, fruit);
    }
}
