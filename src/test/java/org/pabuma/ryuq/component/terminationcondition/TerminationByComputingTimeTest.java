package org.pabuma.ryuq.component.terminationcondition;

import org.junit.jupiter.api.Test;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByComputingTime;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TerminationByComputingTimeTest {
  @Test
  public void shouldConstructorCreateANonNullObject() {
    assertNotNull(new TerminationByComputingTime(100)) ;
  }

  @Test
  public void shouldConstructorCreateAValidObject() {
    long maxComputingTime = 5000 ;
    TerminationByComputingTime termination = new TerminationByComputingTime(maxComputingTime) ;

    assertEquals(maxComputingTime, termination.getMaximumComputingTime());
  }

  @Test
  public void shouldIsMetRaiseAnExceptionIfTheMapDoesNotContainTheRequiredField() {
    Map<String,Object> map = new HashMap<>();
    assertThrows(RuntimeException.class, () -> new TerminationByComputingTime(100).isMet(map)) ;
  }

  @Test
  public void shouldIsMetReturnTrueIfTheConditionIsFulfilled() {
    Map<String,Object> map = new HashMap<>();
    map.put("COMPUTING_TIME", 1001) ;

    int maxComputingTime = 1000 ;

    TerminationByComputingTime termination = new TerminationByComputingTime(maxComputingTime) ;
    assertTrue(termination.isMet(map));
  }

  @Test
  public void shouldIsMetReturnFalseIfTheConditionIsNotFulfilled() {
    Map<String,Object> map = new HashMap<>();
    map.put("COMPUTING_TIME", 999) ;

    int maxComputingTime = 1000 ;

    TerminationByComputingTime termination = new TerminationByComputingTime(maxComputingTime) ;
    assertFalse(termination.isMet(map));
  }
}