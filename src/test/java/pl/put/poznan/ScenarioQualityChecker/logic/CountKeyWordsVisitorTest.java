package pl.put.poznan.ScenarioQualityChecker.logic;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.ScenarioQualityChecker.model.Scenario;
import pl.put.poznan.ScenarioQualityChecker.model.Step;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class CountKeyWordsVisitorTest {

    private CountKeyWordsVisitor visitor;
    private Scenario scenario;

    @Before
    public void setUp() {
        visitor = new CountKeyWordsVisitor();
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
    public void testNoKeyWords() {
        Step step = createStep("Regular step", Collections.emptyList());
        scenario.setSteps(singletonList(step));

        visitor.analyzeScenario(scenario);
        assertEquals(0, visitor.getNumberOfKeyWord());
    }

    @Test
    public void testSingleKeyWord() {
        Step step = createStep("IF condition is met", Collections.emptyList());
        scenario.setSteps(singletonList(step));

        visitor.analyzeScenario(scenario);
        assertEquals(1, visitor.getNumberOfKeyWord());
    }

    @Test
    public void testMultipleKeyWords() {
        Step step1 = createStep("IF condition", Collections.emptyList());
        Step step2 = createStep("FOR EACH item", Collections.emptyList());
        scenario.setSteps(Arrays.asList(step1, step2));

        visitor.analyzeScenario(scenario);
        assertEquals(2, visitor.getNumberOfKeyWord());
    }

    @Test
    public void testNestedScenarios() {
        Step nestedStep = createStep("IF nested condition", Collections.emptyList());
        Scenario subScenario = createScenario(singletonList(nestedStep));
        Step step = createStep("Regular step", singletonList(subScenario));

        scenario.setSteps(singletonList(step));
        visitor.analyzeScenario(scenario);
        assertEquals(1, visitor.getNumberOfKeyWord());
    }

    @Test
    public void testComplexScenario() {
        Step nestedStep1 = createStep("IF nested condition", Collections.emptyList());
        Step nestedStep2 = createStep("FOR EACH element", Collections.emptyList());
        Scenario subScenario = createScenario(Arrays.asList(nestedStep1, nestedStep2));
        Step step = createStep("Regular step", singletonList(subScenario));

        scenario.setSteps(singletonList(step));
        visitor.analyzeScenario(scenario);
        assertEquals(2, visitor.getNumberOfKeyWord());
    }

    @Test
    public void testKeyWordInSubScenarioOnly() {
        Step nestedStep = createStep("IF nested condition", Collections.emptyList());
        Scenario subScenario = createScenario(singletonList(nestedStep));
        Step mainStep = createStep("Regular step", singletonList(subScenario));
        scenario.setSteps(singletonList(mainStep));
        visitor.analyzeScenario(scenario);
        assertEquals(1, visitor.getNumberOfKeyWord());
    }

    @Test
    public void testMultipleKeyWordsInDifferentLevels() {
        Step deepestNestedStep = createStep("FOR EACH deepest level", Collections.emptyList());
        Scenario deepestSubScenario = createScenario(singletonList(deepestNestedStep));
        Step deepNestedStep = createStep("Regular deep step", singletonList(deepestSubScenario));
        Scenario mainScenario = createScenario(singletonList(deepNestedStep));
        Step mainStep = createStep("IF main condition", singletonList(mainScenario));
        scenario.setSteps(singletonList(mainStep));
        visitor.analyzeScenario(scenario);
        assertEquals(2, visitor.getNumberOfKeyWord());
    }

    @Test
    public void testKeyWordsInParallelScenarios() {
        Step nestedStep1 = createStep("IF nested condition 1", Collections.emptyList());
        Step nestedStep2 = createStep("FOR EACH nested condition 2", Collections.emptyList());
        Scenario subScenario1 = createScenario(singletonList(nestedStep1));
        Scenario subScenario2 = createScenario(singletonList(nestedStep2));
        Step mainStep = createStep("Regular main step", Arrays.asList(subScenario1, subScenario2));
        scenario.setSteps(singletonList(mainStep));
        visitor.analyzeScenario(scenario);
        assertEquals(2, visitor.getNumberOfKeyWord());
    }

    @Test
    public void testNoKeyWordsWithNestedScenarios() {
        Step nestedStep = createStep("Regular nested step", Collections.emptyList());
        Scenario subScenario = createScenario(singletonList(nestedStep));
        Step mainStep = createStep("Main regular step", singletonList(subScenario));
        scenario.setSteps(singletonList(mainStep));
        visitor.analyzeScenario(scenario);
        assertEquals(0, visitor.getNumberOfKeyWord());
    }

    @Test
    public void testComplexScenarioWithVariousKeyWords() {
        Step nestedStep1 = createStep("IF condition in nested step 1", Collections.emptyList());
        Step nestedStep2 = createStep("Regular nested step 2", Collections.emptyList());
        Step nestedStep3 = createStep("FOR EACH item in nested step 3", Collections.emptyList());
        Scenario subScenario1 = createScenario(singletonList(nestedStep1));
        Scenario subScenario2 = createScenario(singletonList(nestedStep2));
        Scenario subScenario3 = createScenario(singletonList(nestedStep3));
        Step mainStep1 = createStep("IF main condition 1", singletonList(subScenario1));
        Step mainStep2 = createStep("FOR EACH main item 2", Arrays.asList(subScenario2, subScenario3));
        scenario.setSteps(Arrays.asList(mainStep1, mainStep2));
        visitor.analyzeScenario(scenario);
        assertEquals(4, visitor.getNumberOfKeyWord());
    }
}

