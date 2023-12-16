package pl.put.poznan.ScenarioQualityChecker.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.ScenarioQualityChecker.logic.CountScenarioStepsVisitor;
import pl.put.poznan.ScenarioQualityChecker.model.Scenario;

import java.util.Map;


@RestController
@RequestMapping("/api")
public class ScenarioController {

    @GetMapping
    public String test(){
        return "test123";
    }

    @PostMapping("/count-steps-with-substeps")
    public ResponseEntity<?> processScenario(@RequestBody Scenario scenario) {
        CountScenarioStepsVisitor visitor = new CountScenarioStepsVisitor();
        visitor.analyzeScenario(scenario);
        return ResponseEntity.ok(Map.of("numberOfSteps", visitor.getNumberOfSteps()));
    }
}
