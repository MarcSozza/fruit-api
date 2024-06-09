package com.api.fruit.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

@Data
@AllArgsConstructor
public class Fruit implements Identifiable {
    @Id // permet à mongo de générer _id automatiquement
    @Schema(accessMode = READ_ONLY) // permet de mettre la mention 'read_only' dans le swagger
    private String id;
    private String name;
    private String color;
    private int price;
    private int quantity;
    private String description;
    private String image;

    public Fruit(String name, String color, int price, int quantity, String description, String image) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.image = image;
    }


    @Override
    public Boolean hasId() {
        return id != null;
    }
}
