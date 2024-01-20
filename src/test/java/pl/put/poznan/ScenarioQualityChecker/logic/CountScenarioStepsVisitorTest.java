package pl.put.poznan.ScenarioQualityChecker.logic;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.ScenarioQualityChecker.model.Scenario;
import pl.put.poznan.ScenarioQualityChecker.model.Step;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CountScenarioStepsVisitorTest {

    private CountScenarioStepsVisitor visitor;
    private Scenario scenario;

    @Before
    public void setUp() {
        visitor = new CountScenarioStepsVisitor();
        scenario = new Scenario();
    }

    private Step createStep(String text, List<Scenario> subScenarios) {
        Step step = new Step();
        step.setText(text);
        step.setSubScenarios(subScenarios);
        return step;
    }

    private Scenario createScenario(List<Step> steps) {
        Scenario scenario = new Scenario();
        scenario.setSteps(steps);
        return scenario;
    }

    @Test
    public void testNoSteps() {
        scenario.setSteps(Collections.emptyList());
        visitor.analyzeScenario(scenario);
        assertEquals(0, visitor.getNumberOfSteps());
    }

    @Test
    public void testOneStep() {
        Step step = createStep("Single step", Collections.emptyList());
        scenario.setSteps(Collections.singletonList(step));
        visitor.analyzeScenario(scenario);
        assertEquals(1, visitor.getNumberOfSteps());
    }

    @Test
    public void testMultipleSteps() {
        Step step1 = createStep("Step one", Collections.emptyList());
        Step step2 = createStep("Step two", Collections.emptyList());
        scenario.setSteps(Arrays.asList(step1, step2));
        visitor.analyzeScenario(scenario);
        assertEquals(2, visitor.getNumberOfSteps());
    }

    @Test
    public void testNestedScenarios() {
        Step nestedStep = createStep("Nested step", Collections.emptyList());
        Scenario subScenario = createScenario(Collections.singletonList(nestedStep));
        Step step = createStep("Main step", Collections.singletonList(subScenario));
        scenario.setSteps(Collections.singletonList(step));
        visitor.analyzeScenario(scenario);
        assertEquals(2, visitor.getNumberOfSteps());
    }

    @Test
    public void testComplexScenario() {
        Step nestedStep1 = createStep("Nested step 1", Collections.emptyList());
        Step nestedStep2 = createStep("Nested step 2", Collections.emptyList());
        Scenario subScenario = createScenario(Arrays.asList(nestedStep1, nestedStep2));
        Step step = createStep("Main step", Collections.singletonList(subScenario));
        scenario.setSteps(Collections.singletonList(step));
        visitor.analyzeScenario(scenario);
        assertEquals(3, visitor.getNumberOfSteps());
    }

    @Test
    public void testEmptyNestedScenarios() {
        Step nestedStep = createStep("Nested step", Collections.emptyList());
        Scenario subScenario = createScenario(Collections.singletonList(nestedStep));
        Step emptyNestedStep = createStep("Empty nested step", Collections.singletonList(subScenario));
        scenario.setSteps(Collections.singletonList(emptyNestedStep));
        visitor.analyzeScenario(scenario);
        assertEquals(2, visitor.getNumberOfSteps());
    }

    @Test
    public void testMultipleNestedScenarios() {
        Step innerNestedStep = createStep("Inner nested step", Collections.emptyList());
        Scenario innerSubScenario = createScenario(Collections.singletonList(innerNestedStep));
        Step outerNestedStep = createStep("Outer nested step", Collections.singletonList(innerSubScenario));
        Scenario outerSubScenario = createScenario(Collections.singletonList(outerNestedStep));
        Step mainStep = createStep("Main step", Collections.singletonList(outerSubScenario));
        scenario.setSteps(Collections.singletonList(mainStep));
        visitor.analyzeScenario(scenario);
        assertEquals(3, visitor.getNumberOfSteps());
    }

    @Test
    public void testScenarioWithMultipleStepsAndNestedScenarios() {
        Step step1 = createStep("Step one", Collections.emptyList());
        Step nestedStep = createStep("Nested step", Collections.emptyList());
        Scenario subScenario = createScenario(Collections.singletonList(nestedStep));
        Step step2 = createStep("Step two", Collections.singletonList(subScenario));
        scenario.setSteps(Arrays.asList(step1, step2));
        visitor.analyzeScenario(scenario);
        assertEquals(3, visitor.getNumberOfSteps());
    }

    @Test
    public void testDeeplyNestedScenario() {
        Step deepestNestedStep = createStep("Deepest nested step", Collections.emptyList());
        Scenario deepestSubScenario = createScenario(Collections.singletonList(deepestNestedStep));
        Step deeperNestedStep = createStep("Deeper nested step", Collections.singletonList(deepestSubScenario));
        Scenario deeperSubScenario = createScenario(Collections.singletonList(deeperNestedStep));
        Step deepNestedStep = createStep("Deep nested step", Collections.singletonList(deeperSubScenario));
        Scenario deepSubScenario = createScenario(Collections.singletonList(deepNestedStep));
        Step mainStep = createStep("Main step", Collections.singletonList(deepSubScenario));
        scenario.setSteps(Collections.singletonList(mainStep));
        visitor.analyzeScenario(scenario);
        assertEquals(4, visitor.getNumberOfSteps());
    }

    @Test
    public void testMultipleScenariosWithMultipleSteps() {
        Step nestedStep1 = createStep("Nested step 1", Collections.emptyList());
        Step nestedStep2 = createStep("Nested step 2", Collections.emptyList());
        Scenario subScenario1 = createScenario(Collections.singletonList(nestedStep1));
        Scenario subScenario2 = createScenario(Collections.singletonList(nestedStep2));
        Step mainStep1 = createStep("Main step 1", Collections.singletonList(subScenario1));
        Step mainStep2 = createStep("Main step 2", Collections.singletonList(subScenario2));
        scenario.setSteps(Arrays.asList(mainStep1, mainStep2));
        visitor.analyzeScenario(scenario);
        assertEquals(4, visitor.getNumberOfSteps());
    }

}
