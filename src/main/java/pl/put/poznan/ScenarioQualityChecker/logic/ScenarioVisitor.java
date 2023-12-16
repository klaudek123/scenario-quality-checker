package pl.put.poznan.ScenarioQualityChecker.logic;

import pl.put.poznan.ScenarioQualityChecker.model.Scenario;

public interface ScenarioVisitor {
    void analyzeScenario(Scenario scenario);
}