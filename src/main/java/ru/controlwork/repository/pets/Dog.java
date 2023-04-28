package ru.controlwork.repository.pets;

public class Dog extends PetAnimal {
    public Dog(String name) {
        super(name);
        addCommand("Sit");
        addCommand("Bark");
    }
}
