package ru.controlwork.repository.pack_animals;

public class Donkey extends PackAnimal {
    public Donkey(String name) {
        super(name);
        addCommand("Carry");
        addCommand("Bray");
    }
}