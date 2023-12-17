package pl.put.poznan.ScenarioQualityChecker.logic;

import pl.put.poznan.ScenarioQualityChecker.model.Scenario;

// Define an interface for visitors that process Scenarios
public interface ScenarioVisitor {
    // Abstract method to be implemented by classes that analyze scenarios
    void analyzeScenario(Scenario scenario);
}
