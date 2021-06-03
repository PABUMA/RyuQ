package org.pabuma.ryuq.component.terminationcondition;

import org.junit.jupiter.api.Test;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByLimitOfIterationsWithoutImprovement;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TerminationByLimitOfIterationsWithoutImprovementTest {

  @Test
  public void shouldConstructorCreateAValidObject() {
    int iterations = 500 ;
    TerminationByLimitOfIterationsWithoutImprovement termination = new TerminationByLimitOfIterationsWithoutImprovement(iterations) ;

    assertEquals(iterations, termination.getMaximumNumberOfIterationsWithoutImprovement());
  }

  @Test
  public void shouldIsMetRaiseAnExceptionIfTheMapDoesNotContainTheRequiredField() {
    Map<String,Object> map = new HashMap<>();
    assertThrows(RuntimeException.class, () -> new TerminationByLimitOfIterationsWithoutImprovement(100).isMet(map)) ;
  }

  @Test
  public void shouldIsMetReturnTrueIfTheConditionIsFulfilled() {
    Map<String,Object> map = new HashMap<>();
    map.put("NUMBER_OF_ITERATIONS_WITHOUT_IMPROVEMENT", 1001) ;

    int maxComputingTime = 1000 ;

    TerminationByLimitOfIterationsWithoutImprovement termination = new TerminationByLimitOfIterationsWithoutImprovement(maxComputingTime) ;
    assertTrue(termination.isMet(map));
  }

  @Test
  public void shouldIsMetReturnFalseIfTheConditionIsNotFulfilled() {
    Map<String,Object> map = new HashMap<>();
    map.put("NUMBER_OF_ITERATIONS_WITHOUT_IMPROVEMENT", 999) ;

    int maxComputingTime = 1000 ;

    TerminationByLimitOfIterationsWithoutImprovement termination = new TerminationByLimitOfIterationsWithoutImprovement(maxComputingTime) ;
    assertFalse(termination.isMet(map));
  }
}