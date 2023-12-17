package pl.put.poznan.ScenarioQualityChecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Annotation to mark this class as a Spring Boot application
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.ScenarioQualityChecker.controller"})
// This class serves as the entry point for the Scenario Quality Checker application.
public class ScenarioQualityCheckerApplication {

    // The main method - the starting point of the application
    public static void main(String[] args) {
        // Launches the Spring Boot application
        SpringApplication.run(ScenarioQualityCheckerApplication.class, args);
    }
}
