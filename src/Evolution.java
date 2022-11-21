package src;

public class Evolution {
    	
	public static Individual[] initPopulation(int popSize) {
		Individual[] population = new Individual[popSize];

		// we initialize the population with random characters
		for (int i = 0; i < popSize; i++) {
			char[] tempChromosome = new char[Practical1.TARGET.length()];
			for (int j = 0; j < Practical1.TARGET.length(); j++) {
				tempChromosome[j] = Practical1.alphabet[Practical1.generator.nextInt(Practical1.alphabet.length)]; //choose a random letter in the alphabet
			}
			population[i] = new Individual(tempChromosome);
		}
        return population;
	}
    
	public static double calculateFitness(Individual individual) {
		return Double.valueOf(individual.getHits()) / individual.getChromosome().length;
	}

	public static int countHits(Individual individual) {
		int counter = 0;
		for(int i = 0; i < individual.getChromosome().length; i++) {
			if(Character.compare(individual.getChromosome()[i], Practical1.TARGET.charAt(i)) == 0) counter++;
		}
		return counter;
	}

    public static Individual[] nextGeneration(Individual[] currentGen) {
        Individual[] nextGen = new Individual[currentGen.length];
        Individual[] highPerformer = new Individual[20];

        for (int i = 0; i < highPerformer.length; i++) {
            highPerformer[i] = currentGen[i];
        }
        
        // fill next Generation with children of the high-performer
        int counter = 0;
        for(Individual parent1 : highPerformer) {
            for(int i = 0; i < 5; i++) {
                nextGen[counter] = createChild(parent1, highPerformer[Practical1.generator.nextInt(highPerformer.length)]);
                counter++;
            }
        }

        return nextGen;
    }

    public static Individual createChild(Individual parent1, Individual parent2) {
        char[] newChromosome = new char[Practical1.TARGET.length()];

         // random decision if the chromosome of parent 1 or parent 2 will be chosen
         for (int i = 0; i < newChromosome.length; i++) {
             if(Practical1.generator.nextBoolean()) {
                 newChromosome[i] = parent1.getChromosome()[i];
             } else {
                 newChromosome[i] = parent2.getChromosome()[i];
             }
         }
        
        //int split = Practical1.generator.nextInt(newChromosome.length);
        //// random decision if the chromosome of parent 1 or parent 2 will be chosen
        //for (int i = 0; i < newChromosome.length; i++) {
        //    if(i < split) {
        //        newChromosome[i] = parent1.getChromosome()[i];
        //    } else {
        //        newChromosome[i] = parent2.getChromosome()[i];
        //    }
        //}

        // some random mutations (not so likely)
        for (int i = 0; i < newChromosome.length; i++) {
            if(Practical1.generator.nextDouble(10) > 7) {
                newChromosome[i] = Practical1.alphabet[Practical1.generator.nextInt(Practical1.alphabet.length)];
            }
        }

        Individual child = new Individual(newChromosome);
        return child;
    }
    
    public static Individual[] calculateFitnessForGeneration(Individual[] gen) {
        for(Individual individual : gen) {
            individual.setHits();
            individual.setFitness();
        }
        return gen;
    }
}
