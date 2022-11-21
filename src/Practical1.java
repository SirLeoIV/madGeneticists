package src;
import java.util.ArrayList;
import java.util.Random;

import src.support.Debug;
import src.support.Input;

public class Practical1 {

	public static String TARGET = "HELLO WORLD";
	public static int popSize = 100;
	public static char[] alphabet = new char[27];
	public static Individual[] population = {};
	public static Random generator = new Random(System.currentTimeMillis());
	public static ArrayList<Generation> generations = new ArrayList<>();
	public static ArrayList<Integer> results = new ArrayList<>();
	public static int performersToBeLogged = 3;
	public static int runs = 1;

	public static void main(String[] args) {
		initAlphabet();
		Input.specifyLogLevel();
		Input.specifyTarget();
		Input.specifyChromosomeDesicion();
		Input.specifyPopulationSize();
		Input.specifyNumberOfIndividualsUsedForReproduction();
		Input.specifyNumberOfRuns();

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

		for (int i = 1; i <= runs; i++) {
			runSimulation();
			if(i % 20 == 0) Debug.log1("Run no." + i);
			if(i % 100 == 0) Debug.log1("Medium number of generations (so far): " + results.stream().mapToInt(it -> Integer.valueOf(it)).sum() / results.size());
		}
		if(runs > 1) {
			Debug.log1("");
			Debug.log1("----------- SUCCESSFULLY COMPLETED SIMULATION-----------");
			Debug.log1("Simulation run for " + runs + " times.");
			Debug.log1("Medium number of generations: " + results.stream().mapToInt(it -> Integer.valueOf(it)).sum() / results.size());
		}
	}

	public static void runSimulation() {
		generations = new ArrayList<>();
		Generation currentGeneration = new Generation(Evolution.initPopulation(popSize));
		generations.add(currentGeneration);
		currentGeneration.perform();

		while(currentGeneration.searchForMatch(TARGET) == null) {
		 	currentGeneration = currentGeneration.nextGeneration();
		 	currentGeneration.perform();
			generations.add(currentGeneration);
		 	Debug.log2("Generation: " + generations.size() + ", Medium Fitness: " + currentGeneration.mediumFitness());
			Debug.log3("High Performer: ");
			for(Individual i : currentGeneration.highPerformer(performersToBeLogged)) Debug.log3(i);
			Debug.log3("Low Performer: ");
			for(Individual i : currentGeneration.lowPerformer(performersToBeLogged)) Debug.log3(i);
			Debug.log3("----------------");
		}

		Debug.log2("# SUCCESS #");
		Debug.log1("-- Final Generation: " + generations.size() + ", Medium Fitness: " + currentGeneration.mediumFitness());
		Debug.log1("-- Found Target: " + currentGeneration.searchForMatch(TARGET));
		results.add(generations.size());
	}


	private static void initAlphabet() {
		for (char c = 'A'; c <= 'Z'; c++) {
			alphabet[c - 'A'] = c;
		}
		alphabet[26] = ' ';
	}
}
