package com.example.project;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

// Define the Creature class representing individual creatures in the world
class Creature {
    private double reproductionChance;
    private double deathChance;

    // Constructor to initialize creature's chances of reproduction and death
    public Creature(double reproductionChance, double deathChance) {
        this.reproductionChance = reproductionChance;
        this.deathChance = deathChance;
    }

    // Method to simulate the death of a creature
    public void die() {
        System.out.println("Creature died");
    }

    // Method to simulate the reproduction of a creature
    public Creature reproduce() {
        if (Math.random() < reproductionChance) {
            System.out.println("Creature reproduced");
            return new Creature(reproductionChance, deathChance);
        }
        return null;
    }
}

// Define the World class representing the environment where creatures live
class World {
    private List<Creature> creatures = new ArrayList<>();
    private double spawnChance;

    // Constructor to initialize the world's chance of spawning new creatures
    public World(double spawnChance) {
        this.spawnChance = spawnChance;
    }

    // Method to simulate the creation of a new creature
    public void createCreature() {
        if (Math.random() < spawnChance) {
            Creature newCreature = new Creature(0.5, 0.2); // Example chances
            creatures.add(newCreature);
            System.out.println("New creature created");
        }
    }

    // Method to simulate the spawning of food in the world
    public void spawnFood() {
        System.out.println("Food spawned");
    }

    // Method to simulate the passage of time and interactions among creatures
    public void simulateTimeStep() {
        List<Creature> newCreatures = new ArrayList<>();
        
        // Loop through existing creatures
        for (Creature creature : creatures) {
            // Simulate creature death based on its death chance
            if (Math.random() < creature.deathChance) {
                creature.die();
            } else {
                // Simulate creature reproduction based on its reproduction chance
                Creature offspring = creature.reproduce();
                if (offspring != null) {
                    newCreatures.add(offspring);
                }
            }
        }
        
        // Add newly created creatures to the world's creature list
        creatures.addAll(newCreatures);
    }
}

// Main class to run the simulation
public class Simulation {
    public static void main(String[] args) {
        World world = new World(0.2); // Example spawn chance
        
        // Simulate a number of time steps
        for (int i = 0; i < 10; i++) {
            world.createCreature();
            world.spawnFood();
            world.simulateTimeStep();
            System.out.println("------------");
        }
    }
}
