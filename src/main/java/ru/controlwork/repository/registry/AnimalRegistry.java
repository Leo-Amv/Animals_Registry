package ru.controlwork.repository.registry;

import ru.controlwork.repository.Animal;
import ru.controlwork.repository.pets.Cat;
import ru.controlwork.repository.pets.Dog;
import ru.controlwork.repository.pets.PetAnimal;
import ru.controlwork.repository.pets.Hamster;
import ru.controlwork.repository.pack_animals.Camel;
import ru.controlwork.repository.pack_animals.Donkey;
import ru.controlwork.repository.pack_animals.PackAnimal;
import ru.controlwork.repository.pack_animals.Horse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalRegistry {
    private final List<PetAnimal> petAnimals = new ArrayList<>();
    private final List<PackAnimal> packAnimals = new ArrayList<>();
    private final Counter counter = new Counter();

    public void addAnimal(Animal animal) {
        if (animal instanceof PetAnimal) {
            petAnimals.add((PetAnimal)animal);
        } else if (animal instanceof PackAnimal) {
            packAnimals.add((PackAnimal)animal);
        } else {
            throw new IllegalArgumentException("Unknown animal type");
        }
        counter.add();
    }

    public void listCommands(Animal animal) {
        animal.listCommands();
    }

    public void teachCommand(Animal animal, String command) {
        animal.teachCommand(command);
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("1. Add new animal");
            System.out.println("2. List animal commands");
            System.out.println("3. Teach animal command");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character
            switch (choice) {
                case 1:
                    try (Counter c = counter.start()) {
                        addAnimal(createAnimal(scanner));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    listAnimalCommands(scanner);
                    break;
                case 3:
                    teachAnimalCommand(scanner);
                    break;
                case 4:
                    // do nothing, we will exit the loop
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 4);
    }

    private Animal createAnimal(Scanner scanner) {
        System.out.println("Enter animal type (1 = Pets, 2 = Pack animals): ");
        int type = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        System.out.println("Enter animal name: ");
        String name = scanner.nextLine().trim();
        if (type == 1) {
            return createDomesticAnimal(name);
        } else if (type == 2) {
            return createDraughtAnimal(name);
        } else {
            throw new IllegalArgumentException("Unknown animal type");
        }
    }

    private PetAnimal createDomesticAnimal(String name) {
        System.out.println("Enter animal species (1 = Dog, 2 = Cat, 3 = Hamster): ");
        Scanner scanner = new Scanner(System.in);
        int species = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        return switch (species) {
            case 1 -> new Dog(name);
            case 2 -> new Cat(name);
            case 3 -> new Hamster(name);
            default -> throw new IllegalArgumentException("Unknown animal species");
        };
    }

    private PackAnimal createDraughtAnimal(String name) {
        System.out.println("Enter animal species (1 = Horse, 2 = Camel, 3 = Donkey): ");
        Scanner scanner = new Scanner(System.in);
        int species = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        return switch (species) {
            case 1 -> new Horse(name);
            case 2 -> new Camel(name);
            case 3 -> new Donkey(name);
            default -> throw new IllegalArgumentException("Unknown animal species");
        };
    }

    private void listAnimalCommands(Scanner scanner) {
        System.out.println("Enter animal name: ");
        String name = scanner.nextLine().trim();
        Animal animal = findAnimal(name);
        if (animal != null) {
            listCommands(animal);
        } else {
            System.out.println("Animal not found");
        }
    }

    private void teachAnimalCommand(Scanner scanner) {
        System.out.println("Enter animal name: ");
        String name = scanner.nextLine().trim();
        Animal animal = findAnimal(name);
        if (animal != null) {
            System.out.println("Enter command: ");
            String command = scanner.nextLine().trim();
            teachCommand(animal, command);
        } else {
            System.out.println("Animal not found");
        }
    }

    private Animal findAnimal(String name) {
        for (PetAnimal animal : petAnimals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                return animal;
            }
        }
        for (PackAnimal animal : packAnimals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                return animal;
            }
        }
        return null;
    }

    private static class Counter implements AutoCloseable {
        private int count = 0;

        public Counter start() {
            count++;
            return this;
        }

        public void add() {
            count++;
        }

        @Override
        public void close() throws Exception {
            if (count != 1) {
                throw new Exception("Invalid usage: Counter should be used in try-with-resources block");
            }
        }
    }
}