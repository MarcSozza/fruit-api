package com.api.fruit.services;

import com.api.fruit.models.Fruit;
import com.api.fruit.repositories.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FruitService {

    @Autowired
    public FruitRepository repo;

    public List<Fruit> getAllFruits() {
        return repo.findAll();
    }

    public Fruit addFruit(Fruit fruit) {
        return repo.save(fruit);
    }

    public Fruit updateFruit(int id, Fruit fruit) {
        Optional<Fruit> optionalFruit = repo.findById(String.valueOf(id));

        if(optionalFruit.isPresent()) {
            Fruit updatedFruit = optionalFruit.get();
            updatedFruit.setName(fruit.getName());
            updatedFruit.setPrice(fruit.getPrice());
            updatedFruit.setImage(fruit.getImage());
            updatedFruit.setDescription(fruit.getDescription());
            updatedFruit.setColor(fruit.getColor());

            return repo.save(updatedFruit);
        }
        return null;
    }
}
