package pl.put.poznan.ScenarioQualityChecker.logic;

import lombok.Getter;
import pl.put.poznan.ScenarioQualityChecker.model.Scenario;
import pl.put.poznan.ScenarioQualityChecker.model.Step;

/**
 * Class responsible for counting the occurrence of key words in a scenario.
 * It implements the {@link ScenarioVisitor} interface to allow traversing through the scenario structure.
 */

// Class to count key-words in a scenario, implements the ScenarioVisitor interface
@Getter
public class CountKeyWordsVisitor implements ScenarioVisitor{
    /**
     * Counter for the number of key-words found in the scenario.
     */
    private int numberOfKeyWord = 0; // Counter for the number of key-words

    /**
     * Visits a {@link Scenario} object and processes it to count key words.
     * Iterates over each step in the scenario to perform the count.
     *
     * @param scenario The scenario to visit and analyze.
     */
    // Method to visit and process a Scenario object
    public void visitScenario(Scenario scenario) {
        // Iterate over each step in the scenario
        for (Step step : scenario.getSteps()) {
            // Visit each step to count key words
            visitStep(step);
        }
    }

    /**
     * Processes individual steps in the scenario. Checks if the step's text starts with specific key words,
     * such as 'IF' or 'FOR EACH', and increments the key-word counter accordingly.
     * Also handles sub-scenarios recursively.
     *
     * @param step The step to analyze for key words.
     */
    // Method to process individual steps in the scenario
    public void visitStep(Step step) {
        // Check if the step's text starts with 'IF' or 'FOR EACH' and increment the keyword counter
        if(step.getText().startsWith("IF") || step.getText().startsWith("FOR EACH")){
            numberOfKeyWord++;
        }
        // Check for sub-scenarios within the step and recursively visit them
        if (step.getSubScenarios() != null) {
            for (Scenario subScenario : step.getSubScenarios()) {
                visitScenario(subScenario);
            }
        }
    }


    /**
     * Starts the analysis of the provided scenario. This method overrides the {@code analyzeScenario}
     * method from the {@link ScenarioVisitor} interface.
     *
     * @param scenario The scenario to analyze.
     */
    // Override the analyzeScenario method from the ScenarioVisitor interface
    @Override
    public void analyzeScenario(Scenario scenario) {
        // Begin the analysis by visiting the scenario
        visitScenario(scenario);
    }
}
