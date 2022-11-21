package src;

public class Input {
    
    public static void specifyLogLevel() {
        String[] options = {"1", "2", "3", "4", "help", "?"};

        System.out.println("What log-level do you like to set? (1-4 or \"?\" for explanation)");

        switch(ConsoleIN.readInputOptions(options).toLowerCase()) {
            case "1" -> Debug.LOG_LEVEL = 1;
            case "2" -> Debug.LOG_LEVEL = 2;
            case "3" -> Debug.LOG_LEVEL = 3;
            case "4" -> Debug.LOG_LEVEL = 4;
            case "help", "?" -> {
                System.out.println("Log-Level 1: See only basic information like how many generation were needed for finding the target string.");
                System.out.println("Log-Level 2: See some more information like the medium fitness score of each generation.");
                System.out.println("Log-Level 3: See even more information like the top 3 High- and Low-performers of each generation.");
                System.out.println("Log-Level 4: See a lot of information about e.g. the creation of new individuals");
                specifyLogLevel();
            }
        }
    }
    
    public static void specifyChromosomeDesicion() {
        String[] options = {"1", "2"};

        System.out.println("How recombination-method should be used?");
        System.out.println("    1: one cross-over point");
        System.out.println("    2: multiple cross-over points");

        switch(ConsoleIN.readInputOptions(options).toLowerCase()) {
            case "1" -> Evolution.singleCrossOverPoint = true;
            case "2" -> Evolution.singleCrossOverPoint = false;
        }
    }
    
    public static void specifyTarget() {
        System.out.println("What should be the target String? (A-Z, \" \") (leave blank for \"HELLO WORLD\")");
        String input = ConsoleIN.readAnyInput().toUpperCase();
        if (!input.isBlank()) Practical1.TARGET = input;
    }
    
    public static void specifyPopulationSize() {
        System.out.println("How big should the population be? (leave blank for 100)");
        Integer input = ConsoleIN.readInt();
        if (input != null && input > 0) Practical1.popSize = input;
    }
    
    public static void specifyNumberOfIndividualsUsedForReproduction() {
        System.out.println("How many of the best performing individuals should be used for reproduction? (leave blank for population-size / 5)");
        Integer input = ConsoleIN.readInt();
        if (input != null && input > 0) Evolution.numberOfIndividualsUsedForReproduction = input;
        else Evolution.numberOfIndividualsUsedForReproduction = Practical1.popSize / 5;
    }
}
