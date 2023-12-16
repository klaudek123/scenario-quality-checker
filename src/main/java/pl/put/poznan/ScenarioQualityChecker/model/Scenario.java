package pl.put.poznan.ScenarioQualityChecker.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class Scenario {
    private String title;
    private List<String> actors;
    private List<Step> steps;
}