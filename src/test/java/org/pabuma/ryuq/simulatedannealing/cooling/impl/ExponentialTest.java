package org.pabuma.ryuq.simulatedannealing.cooling.impl;

import org.apache.commons.math3.analysis.function.Exp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExponentialTest {
  @Test
  public void shouldConstructorCreateAValidObject() {
    double beta = 0.5;
    Exponential coolingScheme = new Exponential(beta);

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
    Exponential coolingScheme = new Exponential(beta) ;
    double temperatureValue = 0.25 ;
    int iteration = 0 ;

    double expectedValue = temperatureValue / (1 + beta*temperatureValue) ;

    assertEquals(expectedValue, coolingScheme.updateTemperature(temperatureValue, iteration));
  }
}