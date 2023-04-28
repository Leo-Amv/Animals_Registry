package ru.controlwork;

import ru.controlwork.repository.registry.AnimalRegistry;

public class Main {
    public static void main(String[] args) {
        AnimalRegistry registry = new AnimalRegistry();
        registry.displayMenu();
    }
}