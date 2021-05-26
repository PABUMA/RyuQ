package org.pabuma.ryuq.component.terminationcondition;

import org.junit.jupiter.api.Test;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByComputingTime;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TerminationByEvaluationsTest {
  @Test
  public void shouldConstructorCreateANonNullObject() {
    assertNotNull(new TerminationByEvaluations(100)) ;
  }

  @Test
  public void shouldConstructorCreateAValidObject() {
    int maxEvaluations = 5000 ;
    TerminationByEvaluations termination = new TerminationByEvaluations(maxEvaluations) ;

    assertEquals(maxEvaluations, termination.getMaximumNumberOfEvaluations());
  }

  @Test
  public void shouldIsMetRaiseAnExceptionIfTheMapDoesNotContainTheRequiredField() {
    Map<String,Object> map = new HashMap<>();
    assertThrows(RuntimeException.class, () -> new TerminationByEvaluations(100).isMet(map)) ;
  }

  @Test
  public void shouldIsMetReturnTrueIfTheConditionIsFulfilled() {
    Map<String,Object> map = new HashMap<>();
    map.put("EVALUATIONS", 1001) ;

    int maxComputingTime = 1000 ;

    TerminationByEvaluations termination = new TerminationByEvaluations(maxComputingTime) ;
    assertTrue(termination.isMet(map));
  }

  @Test
  public void shouldIsMetReturnFalseIfTheConditionIsNotFulfilled() {
    Map<String,Object> map = new HashMap<>();
    map.put("EVALUATIONS", 999) ;

    int maxComputingTime = 1000 ;

    TerminationByEvaluations termination = new TerminationByEvaluations(maxComputingTime) ;
    assertFalse(termination.isMet(map));
  }
}