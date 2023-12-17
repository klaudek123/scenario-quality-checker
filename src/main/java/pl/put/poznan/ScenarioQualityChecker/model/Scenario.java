package pl.put.poznan.ScenarioQualityChecker.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// Lombok annotations to simplify the creation of data model classes
@Data
@Getter
@Setter

// Class representing a Scenario in the system.
public class Scenario {
    private String title; // Field to store the title of the scenario
    private List<String> actors; // List to store the actors involved in the scenario
    private List<Step> steps; // List to store the steps of the scenario
}
