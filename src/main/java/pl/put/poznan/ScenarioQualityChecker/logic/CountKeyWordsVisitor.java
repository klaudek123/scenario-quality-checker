package pl.put.poznan.ScenarioQualityChecker.logic;

import lombok.Getter;
import pl.put.poznan.ScenarioQualityChecker.model.Scenario;
import pl.put.poznan.ScenarioQualityChecker.model.Step;

// Class to count key-words in a scenario, implements the ScenarioVisitor interface
@Getter
public class CountKeyWordsVisitor implements ScenarioVisitor{
    private int numberOfKeyWord = 0; // Counter for the number of key-words

    // Method to visit and process a Scenario object
    public void visitScenario(Scenario scenario) {
        // Iterate over each step in the scenario
        for (Step step : scenario.getSteps()) {
            // Visit each step to count key words
            visitStep(step);
        }
    }

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

    // Override the analyzeScenario method from the ScenarioVisitor interface
    @Override
    public void analyzeScenario(Scenario scenario) {
        // Begin the analysis by visiting the scenario
        visitScenario(scenario);
    }
}
