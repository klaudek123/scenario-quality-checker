package pl.put.poznan.ScenarioQualityChecker.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.ScenarioQualityChecker.logic.CountKeyWordsVisitor;
import pl.put.poznan.ScenarioQualityChecker.logic.CountScenarioStepsVisitor;
import pl.put.poznan.ScenarioQualityChecker.model.Scenario;

import java.util.Map;

// Define a REST Controller for handling scenario-related requests
@RestController
@RequestMapping("/api")
public class ScenarioController {

    // A simple test method accessible via a GET request
    @GetMapping
    public String test(){
        return "test123";
    }

    // Endpoint to count steps and substeps in a scenario using POST request
    @PostMapping("/count-steps-with-substeps")
    public ResponseEntity<?> countSteps(@RequestBody Scenario scenario) {
        // Create a new visitor instance to count scenario steps
        CountScenarioStepsVisitor visitor = new CountScenarioStepsVisitor();
        // Analyze the scenario with the visitor
        visitor.analyzeScenario(scenario);
        // Return the number of steps found in the scenario
        return ResponseEntity.ok(Map.of("numberOfSteps", visitor.getNumberOfSteps()));
    }

    // Endpoint to count key-words in a scenario using POST request
    @PostMapping("/count-key-words")
    public ResponseEntity<?> countKeyWords(@RequestBody Scenario scenario) {
        // Create a new visitor instance to count key-words
        CountKeyWordsVisitor visitor = new CountKeyWordsVisitor();
        // Analyze the scenario with the visitor
        visitor.analyzeScenario(scenario);
        // Return the number of key-words found in the scenario
        return ResponseEntity.ok(Map.of("numberOfKeyWords", visitor.getNumberOfKeyWord()));
    }
}
