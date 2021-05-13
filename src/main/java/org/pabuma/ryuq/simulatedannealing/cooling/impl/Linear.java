package org.pabuma.ryuq.simulatedannealing.cooling.impl;

import org.pabuma.ryuq.simulatedannealing.SimulatedAnnealing;
import org.pabuma.ryuq.simulatedannealing.cooling.CoolingScheme;

/**
 * Linear cooling scheme used by {@link SimulatedAnnealing} algorithms
 */
public class Linear implements CoolingScheme {
  private final double beta ;

  public Linear(double beta) {
    this.beta = beta ;
  }

  @Override
  public double updateTemperature(double temperature) {
    return temperature - beta ;
  }
}
