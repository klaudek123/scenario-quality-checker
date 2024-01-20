package pl.put.poznan.ScenarioQualityChecker.model;

import lombok.*;

import java.util.List;

// Lombok annotations to simplify the creation of data model classes
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// Class representing a Scenario in the system.
public class Scenario {
    private String title; // Field to store the title of the scenario
    private List<String> actors; // List to store the actors involved in the scenario
    private String systemActor; // Field to store type of system actor
    @Getter
    private List<Step> steps = new ArrayList<>();

    public Scenario(Step step) {
        steps.add(step);
    }
}
