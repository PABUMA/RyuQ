package org.pabuma.ryuq.simulatedannealing.cooling.impl;

import org.pabuma.ryuq.simulatedannealing.SimulatedAnnealing;
import org.pabuma.ryuq.simulatedannealing.cooling.CoolingScheme;

/**
 * Linear cooling scheme used by {@link SimulatedAnnealing} algorithms
 */
public class Linear implements CoolingScheme {
  private final double beta ;

  public Linear(double beta) {
    if (beta <= 0) {
      throw new RuntimeException("The value of beta (" + beta + ") must be greater than zero") ;
    }
    this.beta = beta ;
  }

  @Override
  public double updateTemperature(double temperature) {
    return temperature - beta ;
  }

  public double getBeta() {
    return beta ;
  }
}
