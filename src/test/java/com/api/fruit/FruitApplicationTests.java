package com.api.fruit;

import com.api.fruit.models.Fruit;
import com.api.fruit.repositories.FruitRepository;
import com.api.fruit.services.FruitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class FruitApplicationTests {

    @MockBean
    private FruitRepository fruitRepository;

    @Autowired
    private FruitService fruitService;


    /**
     * Retrieves all fruits.
     *
     * @return a List of fruits
     */
    @Test
    void getAllFruits() throws Exception {
        Fruit fruit1 = new Fruit("1", "Apple", "Red", 100, 10, "Good", "apple.png");
        Fruit fruit2 = new Fruit("2", "Apple", "Green", 100, 10, "Good", "apple.png");
        Fruit fruit3 = new Fruit("3", "Apple", "Blue", 100, 10, "Good", "apple.png");

        List<Fruit> allFruits = Arrays.asList(fruit1, fruit2, fruit3);

        when(fruitRepository.findAll()).thenReturn(allFruits);

        List<Fruit> actualFruits = fruitService.getAllFruits();

        verify(fruitRepository, times(1)).findAll();

        assertEquals(allFruits, actualFruits);
    }

    @Test
    void addFruit_failed_if_id_is_send_in_object() throws Exception {
        Fruit fruitToSaved = new Fruit("1", "Apple", "Red", 100, 10, "Good", "apple.png");
        RuntimeException e = assertThrows(RuntimeException.class, () -> fruitService.addFruit(fruitToSaved));

        String expectedMessage = "Not Allowed to put a custom ID";
        String actualMessage = e.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void addFruit_succeed_if_user_not_send_an_id() throws Exception {
        Fruit fruitToSaved = new Fruit("Apple", "Red", 100, 10, "Good", "apple.png");
        when(fruitRepository.save(fruitToSaved)).thenReturn(fruitToSaved);
        Fruit savedFruit = fruitService.addFruit(fruitToSaved);
        assertNotNull(savedFruit);
        assertEquals(fruitToSaved.getName(), savedFruit.getName());
        assertEquals(fruitToSaved.getColor(), savedFruit.getColor());
        assertEquals(fruitToSaved.getPrice(), savedFruit.getPrice());
        assertEquals(fruitToSaved.getImage(), savedFruit.getImage());
        assertEquals(fruitToSaved.getQuantity(), savedFruit.getQuantity());

        verify(fruitRepository, times(1)).save(fruitToSaved);
    }

    /**
     * Updates a fruit in the database based on the provided ID and fruit object.
     * If the fruit object has a custom ID, a RuntimeException will be thrown.
     * If the fruit with the given ID exists in the database, the fruit's properties will be updated and saved.
     * If the fruit with the given ID does not exist in the database, null will be returned.
     *
     * @return the updated fruit object if it exists in the database, otherwise null
     * @throws RuntimeException if the fruit object has a custom ID
     */
    @Test
    void updateFruit_failed_if_custom_id() throws Exception {
        Fruit fruitToUpdated = new Fruit("1", "Apple", "Red", 100, 10, "Good", "apple.png");
        //when(fruitToUpdated.hasId()).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> fruitService.updateFruit("1", fruitToUpdated));

        String expectedMessage = "Not Allowed to put a custom ID";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

    }

    /**
     * Updates a fruit in the database based on the provided ID and fruit object.
     * If the fruit object has a custom ID, a RuntimeException will be thrown.
     * If the fruit with the given ID exists in the database, the fruit's properties will be updated and saved.
     * If the fruit with the given ID does not exist in the database, null will be returned.
     *
     * @return the updated fruit object if it exists in the database, otherwise null
     */
    @Test
    void updateFruit_send_null_if_not_custom_id_but_fruit_doesnt_exist_in_database() throws Exception {
        Fruit fruitByUser = new Fruit("Pineapple", "Orange", 15, 15, "bade", "pineapple.png");
        String idToFind = "15";

        when(fruitRepository.findById(idToFind)).thenReturn(Optional.empty());

        Fruit updatedFruit = fruitService.updateFruit(idToFind, fruitByUser);
        assertNull(updatedFruit);
    }

    @Test
    void updateFruit_success_if_not_custom_id_and_fruit_exist_in_database() throws Exception {
        Fruit fruit1 = new Fruit("1", "Pineapple", "Red", 100, 10, "Good", "apple.png");

        Fruit fruitByUser = new Fruit("Apple", "Blue", 15, 36, "Very Good", "goodApple.png");
        String idFruitToUpdate = "1";

        when(fruitRepository.findById(idFruitToUpdate)).thenReturn(Optional.of(fruit1));
        when(fruitRepository.save(any(Fruit.class))).thenReturn(fruitByUser);

        Fruit updatedFruit = fruitService.updateFruit(idFruitToUpdate, fruitByUser);

        assertEquals(updatedFruit.getName(), fruitByUser.getName());
        assertEquals(updatedFruit.getColor(), fruitByUser.getColor());
        assertEquals(updatedFruit.getDescription(), fruitByUser.getDescription());
        assertEquals(updatedFruit.getQuantity(), fruitByUser.getQuantity());
        assertEquals(updatedFruit.getPrice(), fruitByUser.getPrice());

    }

}
