package ru.controlwork.repository.pack_animals;

public class Camel extends PackAnimal {
    public Camel(String name) {
        super(name);
        addCommand("Carry");
        addCommand("Walk");
    }
}