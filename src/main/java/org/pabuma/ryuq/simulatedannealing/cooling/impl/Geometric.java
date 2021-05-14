package org.pabuma.ryuq.simulatedannealing.cooling.impl;

import org.pabuma.ryuq.simulatedannealing.SimulatedAnnealing;
import org.pabuma.ryuq.simulatedannealing.cooling.CoolingScheme;

/**
 * Geometric cooling scheme used by {@link SimulatedAnnealing} algorithms
 */
public class Geometric implements CoolingScheme {
  private final double alpha ;

  public Geometric(double alpha) {
    if ((alpha < 0.0) || (alpha > 1.0)) {
      throw new RuntimeException("The value of alpha (" + alpha + ") must be a number between 0 and 1") ;
    }
    this.alpha = alpha ;
  }

  @Override
  public double updateTemperature(double temperature) {
    return alpha * temperature ;
  }

  public double getAlpha() {
    return alpha ;
  }
}
