package src;
import java.util.ArrayList;
import java.util.Random;

public class Practical1 {

	static String TARGET = "HELLO WORLD";
	static int popSize = 100;
	static char[] alphabet = new char[27];
	static Individual[] population = {};
	static Random generator = new Random(System.currentTimeMillis());
	static ArrayList<Generation> generations = new ArrayList<>();
	static ArrayList<Integer> results = new ArrayList<>();

	public static void main(String[] args) {
		initAlphabet();

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

		for (int i = 0; i < 1; i++) {
			runSimulation();
			if(i % 20 == 0) System.out.println("Run no." + i);
			if(i % 100 == 0) System.out.println("Medium number of generations (so far): " + results.stream().mapToInt(it -> Integer.valueOf(it)).sum() / results.size());
		}

		System.out.println("Medium number of generations: " + results.stream().mapToInt(it -> Integer.valueOf(it)).sum() / results.size());
	}

	public static void runSimulation() {
		generations = new ArrayList<>();
		Generation currentGeneration = new Generation(Evolution.initPopulation(popSize));
		generations.add(currentGeneration);
		currentGeneration.perform();
		// for(Individual i : currentGeneration.highPerformer(5)) System.out.println(i);
		// System.out.println("Medium Fitness: " + currentGeneration.mediumFitness());

		while(currentGeneration.searchForMatch(TARGET) == null) {
		 	currentGeneration = currentGeneration.nextGeneration();
		 	currentGeneration.perform();
		 	System.out.println("Generation: " + generations.size() + ", Medium Fitness: " + currentGeneration.mediumFitness());
			generations.add(currentGeneration);
			for(Individual i : currentGeneration.highPerformer(3)) System.out.println(i);
			for(Individual i : currentGeneration.lowPerformer(3)) System.out.println(i);
		}

		// for(Individual i : currentGeneration.highPerformer(2)) System.out.println(i);
		System.out.println("Generation: " + generations.size() + ", Medium Fitness: " + currentGeneration.mediumFitness());
		System.out.println("Found Target: " + currentGeneration.searchForMatch(TARGET));
		results.add(generations.size());
	}


	private static void initAlphabet() {
		for (char c = 'A'; c <= 'Z'; c++) {
			alphabet[c - 'A'] = c;
		}
		alphabet[26] = ' ';
	}
}
