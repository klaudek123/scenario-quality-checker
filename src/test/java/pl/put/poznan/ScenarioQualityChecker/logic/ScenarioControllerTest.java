package pl.put.poznan.ScenarioQualityChecker.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import pl.put.poznan.ScenarioQualityChecker.logic.CountKeyWordsVisitor;
import pl.put.poznan.ScenarioQualityChecker.logic.CountScenarioStepsVisitor;
import pl.put.poznan.ScenarioQualityChecker.model.Scenario;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScenarioControllerTest {

    @InjectMocks
    private ScenarioController scenarioController;



    @Test
    public void testCountSteps() {
        Scenario testScenario = mock(Scenario.class);
        CountScenarioStepsVisitor mockcountScenarioStepsVisitor= mock(CountScenarioStepsVisitor.class);

        when(mockcountScenarioStepsVisitor.getNumberOfSteps()).thenReturn(10);

        ResponseEntity<?> responseEntity = scenarioController.countSteps(testScenario);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(Map.of("numberOfSteps", 10), responseEntity.getBody());

        verify(mockcountScenarioStepsVisitor).analyzeScenario(testScenario);
    }

    @Test
    public void testCountKeyWords() {
        Scenario testScenario = mock(Scenario.class);
        CountKeyWordsVisitor countKeyWordsVisitor = mock(CountKeyWordsVisitor.class);
        when(countKeyWordsVisitor.getNumberOfKeyWord()).thenReturn(5);

        ResponseEntity<?> responseEntity = scenarioController.countKeyWords(testScenario);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(Map.of("numberOfKeyWords", 5), responseEntity.getBody());

        verify(countKeyWordsVisitor).analyzeScenario(testScenario);
    }
}