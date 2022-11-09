package src;
import java.util.Random;

public class Practical1 {

	static final String TARGET = "HELLO WORLD";
	static char[] alphabet = new char[27];

	public static void main(String[] args) {
		int popSize = 100;
		for (char c = 'A'; c <= 'Z'; c++) {
			alphabet[c - 'A'] = c;
		}
		alphabet[26] = ' ';
		Random generator = new Random(System.currentTimeMillis());
		Individual[] population = new Individual[popSize];
		// we initialize the population with random characters
		for (int i = 0; i < popSize; i++) {
			char[] tempChromosome = new char[TARGET.length()];
			for (int j = 0; j < TARGET.length(); j++) {
				tempChromosome[j] = alphabet[generator.nextInt(alphabet.length)]; //choose a random letter in the alphabet
			}
			population[i] = new Individual(tempChromosome);
		}
		// What does your population look like?
		for (int i = 0; i < population.length; i++) {
			population[i].setFitness(calculateFitness(population[i]));
			population[i].setHits(countHits(population[i]));
			// System.out.println(population[i].genoToPhenotype());
			// System.out.println(" : " + population[i].getFitness());
		}

		// do your own cool GA here
		/**
		 * Some general programming remarks and hints:
		 * - A crucial point is to set each individual's fitness (by the setFitness() method) before sorting. When is an individual fit? 
		 * 	How do you encode that into a double (between 0 and 1)?
		 * - Decide when to stop, that is: when the algorithm has converged. And make sure you  terminate your loop when it does.
		 * - print the whole population after convergence and print the number of generations it took to converge.
		 * - print lots of output (especially if things go wrong).
		 * - work in an orderly and structured fashion (use tabs, use methods,..)
		 * - DONT'T make everything private. This will only complicate things. Keep variables local if possible
		 * - A common error are mistakes against pass-by-reference (this means that you pass the 
		 * 	address of an object, not a copy of the object to the method). There is a deepclone method included in the
		 *  Individual class.Use it!
		 * - You can compare your chromosome and your target string, using for eg. TARGET.charAt(i) == ...
		 * - Check your integers and doubles (eg. don't use ints for double divisions).
		 */

		HeapSort.sort(population);

		for(Individual i : population) System.out.println(i);
	}

	public static double calculateFitness(Individual individual) {
		return Double.valueOf(countHits(individual)) / individual.getChromosome().length;
	}

	public static int countHits(Individual individual) {
		int counter = 0;

		for(int i = 0; i < individual.getChromosome().length; i++) {
			if(Character.compare(individual.getChromosome()[i], TARGET.charAt(i)) == 0) counter++;
		}

		return counter;
	}
	
}
