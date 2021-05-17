package org.pabuma.ryuq.simulatedannealing.cooling.impl;

import org.pabuma.ryuq.simulatedannealing.SimulatedAnnealing;
import org.pabuma.ryuq.simulatedannealing.cooling.CoolingScheme;

/**
 * Exponential cooling scheme used by {@link SimulatedAnnealing} algorithms
 */
public class Exponential implements CoolingScheme {
  private final double beta ;

  public Exponential(double beta) {
    if (beta <= 0) {
      throw new RuntimeException("The value of beta (" + beta + ") must be greater than zero") ;
    }
    this.beta = beta ;
  }

  @Override
  /**
   * The iteration number is not needed in this cooling scheme
   */
  public double updateTemperature(double temperature, int iteration) {
    return temperature / (1 + beta*temperature) ;
  }

  public double getBeta() {
    return beta ;
  }
}
