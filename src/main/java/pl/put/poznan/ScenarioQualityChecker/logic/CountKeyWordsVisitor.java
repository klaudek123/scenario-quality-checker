package pl.put.poznan.ScenarioQualityChecker.logic;

import lombok.Getter;
import pl.put.poznan.ScenarioQualityChecker.model.Scenario;
import pl.put.poznan.ScenarioQualityChecker.model.Step;


@Getter
public class CountKeyWordsVisitor implements ScenarioVisitor{
    private int numberOfKeyWord = 0;


    public void visitScenario(Scenario scenario) {
        for (Step step : scenario.getSteps()) {
            visitStep(step);
        }
    }

    public void visitStep(Step step) {
        if(step.getText().startsWith("IF") || step.getText().startsWith("FOR EACH")){
            numberOfKeyWord++;
        }
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
