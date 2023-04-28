package ru.controlwork.repository.pets;

public class Hamster extends PetAnimal {
    public Hamster(String name) {
        super(name);
        addCommand("Run");
        addCommand("Stop");
    }
}