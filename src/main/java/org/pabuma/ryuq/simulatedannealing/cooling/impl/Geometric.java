package org.pabuma.ryuq.simulatedannealing.cooling.impl;

import org.pabuma.ryuq.simulatedannealing.SimulatedAnnealing;
import org.pabuma.ryuq.simulatedannealing.cooling.CoolingScheme;

/**
 * Geometric cooling scheme used by {@link SimulatedAnnealing} algorithms
 */
public class Geometric implements CoolingScheme {
  private final double alpha ;

  public Geometric(double alpha) {
    this.alpha = alpha ;
  }

  @Override
  public double updateTemperature(double temperature) {
    return alpha * temperature ;
  }
}
