package com.api.fruit;

import com.api.fruit.services.FruitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FruitApplicationTests {

    @Autowired
    private FruitService fruitService;

    @Test
    void getAllFruits() throws Exception {

    }

}
