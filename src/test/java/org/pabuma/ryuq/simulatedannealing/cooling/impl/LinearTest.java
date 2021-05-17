package org.pabuma.ryuq.simulatedannealing.cooling.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinearTest {
  @Test
  public void shouldConstructorCreateAValidObject() {
    double beta = 0.5;
    Linear coolingScheme = new Linear(beta);

    assertNotNull(coolingScheme);
    assertEquals(beta, coolingScheme.getBeta());
  }

  @Test
  public void shouldConstructorWithANegativeBetaValueThrowAnException() {
    double beta = -1.0 ;
    assertThrows(RuntimeException.class, () -> new Linear(beta)) ;
  }

  @Test
  public void shouldUpdateTemperatureWorkProperly() {
    double beta = 0.1 ;
    Linear coolingScheme = new Linear(beta) ;
    double temperatureValue = 0.25 ;
    int iteration = 0 ;

    assertEquals(temperatureValue - beta, coolingScheme.updateTemperature(temperatureValue, iteration));
  }
}