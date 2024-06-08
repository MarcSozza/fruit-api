package com.api.fruit.models;

import lombok.Data;

@Data
public class Fruit {
    private int id;
    private String name;
    private String color;
    private int price;
    private int quantity;
    private String description;
    private String image;
}
