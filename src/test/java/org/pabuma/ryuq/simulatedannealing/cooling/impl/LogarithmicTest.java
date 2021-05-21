package org.pabuma.ryuq.simulatedannealing.cooling.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogarithmicTest {
  @Test
  public void shouldConstructorCreateAValidObject() {
    Logarithmic coolingScheme = new Logarithmic();

    assertNotNull(coolingScheme);
  }

  @Test
  public void shouldUpdateTemperatureWorkProperly() {
    Logarithmic coolingScheme = new Logarithmic() ;
    double temperatureValue = 0.25 ;
    int iteration = 4 ;

    double expectedValue = Math.log(iteration)/Math.log(iteration + 1) * temperatureValue ;

    assertEquals(expectedValue, coolingScheme.updateTemperature(temperatureValue, iteration));
  }
}