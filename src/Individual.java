package src;

public class Individual {
	
	private char[] chromosome;
	private double fitness;
	private int hits;
	
	public Individual(char[] chromosome) {
		this.chromosome = chromosome;
		this.fitness = 0;
		this.hits = 0;
	}


	public char[] getChromosome() {
		return chromosome;
	}

	public void setChromosome(char[] chromosome) {
		this.chromosome = chromosome;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public void setFitness() {
		fitness = Evolution.calculateFitness(this);
	}
	
	public String genoToPhenotype() {
		StringBuilder builder = new StringBuilder();
		builder.append(chromosome);
		return builder.toString();
	}
	
	public Individual clone() {
		char[] chromClone = new char[chromosome.length];
		for(int i = 0; i < chromClone.length; i++) {
			chromClone[i] = chromosome[i];
		}
		return new Individual(chromClone);
	}
	
	public String toString() {
		//return "Phenotype:  '" + genoToPhenotype() + "'  Fitness: " + fitness;
		if(Debug.DEBUG) return genoToPhenotype() + " : " + fitness + " (" + hits + "/" + Practical1.TARGET.length() + ")";
		return genoToPhenotype() + " : " + fitness;
	}


	public void setHits(int hits) {
		this.hits = hits;
	}


	public void setHits() {
		hits = Evolution.countHits(this);
	}


    public int getHits() {
        return hits;
    }

	public boolean matches(String target) {
		for(int i = 0; i < chromosome.length; i++) {
			if(Character.compare(chromosome[i], target.charAt(i)) != 0) return false;
		}
		return true;
	}

}
