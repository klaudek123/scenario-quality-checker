package pl.put.poznan.ScenarioQualityChecker.model;

import lombok.*;
import pl.put.poznan.ScenarioQualityChecker.logic.ScenarioVisitor;

import java.util.List;

// Lombok annotations to simplify the creation of data model classes
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

// Class representing a Step in a scenario.
public class Step {
    private String text; // Field to store the text description of the step
    private List<Scenario> subScenarios; // List to store sub-scenarios within this step (if any)
}
