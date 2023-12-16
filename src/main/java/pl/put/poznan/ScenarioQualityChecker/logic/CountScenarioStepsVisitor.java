package pl.put.poznan.ScenarioQualityChecker.logic;

import lombok.Getter;
import pl.put.poznan.ScenarioQualityChecker.model.Scenario;
import pl.put.poznan.ScenarioQualityChecker.model.Step;

@Getter
public class CountScenarioStepsVisitor implements ScenarioVisitor {
    private int numberOfSteps = 0;


    public void visitScenario(Scenario scenario) {
        for (Step step : scenario.getSteps()) {
            visitStep(step);
        }
    }

    public void visitStep(Step step) {
        numberOfSteps++;
        if (step.getSubScenarios() != null) {
            for (Scenario subScenario : step.getSubScenarios()) {
                visitScenario(subScenario);
            }
        }
    }

    @Override
    public void analyzeScenario(Scenario scenario) {
        visitScenario(scenario);
    }
}
