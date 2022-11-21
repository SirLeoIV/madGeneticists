package src;

import java.util.stream.Stream;

public class Generation {
    
    private Individual[] population = {};
    
    public Generation(Individual[] population) {
        this.population = population;
    }

    public Individual[] getPopulation() {
        return population;
    }

    public void setPopulation(Individual[] population) {
        this.population = population;
    }
    
    public void perform() {
        population = Evolution.calculateFitnessForGeneration(population);
		HeapSort.sort(population);
    }
    
    public Generation nextGeneration() {
        return new Generation(Evolution.nextGeneration(population));
    }
    
    public double mediumFitness() {
        return Stream.of(population).mapToDouble(it -> it.getFitness()).sum() / population.length;
    }
    
    public Individual[] highPerformer(int amount) {
        Individual[] highPerformer = new Individual[amount];
        for (int i = 0; i < amount && i < population.length - 1; i++) {
            highPerformer[i] = population[i];
        }
        return highPerformer;
    }

    public Individual[] lowPerformer(int amount) {
        Individual[] lowPerformer = new Individual[amount];
        for (int i = 0; i < amount && i < population.length - 1; i++) {
            lowPerformer[i] = population[population.length - 1 - i];
        }
        return lowPerformer;
    }

    public Individual searchForMatch(String target) {
        Individual result = null;
        for (Individual individual : population) {
            if(individual.matches(target)) return individual;
        }
        return result;
    }
}
