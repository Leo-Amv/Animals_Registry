package ru.controlwork.repository.pets;

public class Cat extends PetAnimal {
    public Cat(String name) {
        super(name);
        addCommand("Jump");
        addCommand("Purr");
    }
}
