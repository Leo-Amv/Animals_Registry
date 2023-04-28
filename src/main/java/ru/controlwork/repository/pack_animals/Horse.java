package ru.controlwork.repository.pack_animals;

public class Horse extends PackAnimal {
    public Horse(String name) {
        super(name);
        addCommand("Walk");
        addCommand("Gallop");
    }
}