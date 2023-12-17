package pl.put.poznan.ScenarioQualityChecker.logic;

import lombok.Getter;
import pl.put.poznan.ScenarioQualityChecker.model.Scenario;
import pl.put.poznan.ScenarioQualityChecker.model.Step;

// This class is responsible for counting the total number of steps in a scenario.
@Getter // Lombok annotation to generate getter methods for all fields
public class CountScenarioStepsVisitor implements ScenarioVisitor {
    private int numberOfSteps = 0; // Counter for the total number of steps

    // Method to process a Scenario object
    public void visitScenario(Scenario scenario) {
        // Iterate over each step in the scenario
        for (Step step : scenario.getSteps()) {
            // Process each step to count it
            visitStep(step);
        }
    }

    // Method to process individual steps in the scenario
    public void visitStep(Step step) {
        // Increment the step counter for each step encountered
        numberOfSteps++;
        // Check if there are sub-scenarios within the current step
        if (step.getSubScenarios() != null) {
            // Recursively visit and count steps in each sub-scenario
            for (Scenario subScenario : step.getSubScenarios()) {
                visitScenario(subScenario);
            }
        }
    }

    // Override the analyzeScenario method from the ScenarioVisitor interface
    @Override
    public void analyzeScenario(Scenario scenario) {
        // Start the step counting process by visiting the scenario
        visitScenario(scenario);
    }
}
