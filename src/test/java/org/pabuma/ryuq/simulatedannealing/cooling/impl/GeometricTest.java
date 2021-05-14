package org.pabuma.ryuq.simulatedannealing.cooling.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeometricTest {
  @Test
  public void shouldConstructorCreateAValidObject() {
    double alpha = 0.5;
    Geometric coolingScheme = new Geometric(alpha);

    assertNotNull(coolingScheme);
    assertEquals(alpha, coolingScheme.getAlpha());
  }

  @Test
  public void shouldConstructorWithANegativeAlphaValueThrowAnException() {
    double alpha = -1.0 ;
    assertThrows(RuntimeException.class, () -> new Geometric(alpha)) ;
  }

  @Test
  public void shouldConstructorWithAneAlphaValueHigherThanOneThrowAnException() {
    double alpha = 1.1;
    assertThrows(RuntimeException.class, () -> new Geometric(alpha)) ;
  }

  @Test
  public void shouldUpdateTemperatureWorkProperly() {
    double alpha = 0.1 ;
    Geometric coolingScheme = new Geometric(alpha) ;
    double temperatureValue = 0.25 ;

    assertEquals(temperatureValue * alpha, coolingScheme.updateTemperature(temperatureValue));
  }
}