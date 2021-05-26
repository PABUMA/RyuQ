package org.pabuma.ryuq.simulatedannealing.cooling.impl;

import org.pabuma.ryuq.simulatedannealing.SimulatedAnnealing;
import org.pabuma.ryuq.simulatedannealing.cooling.CoolingScheme;

/**
 * Logarithmic cooling scheme used by {@link SimulatedAnnealing} algorithms
 */
public class Logarithmic implements CoolingScheme {

  @Override
  public double updateTemperature(double temperature, int iteration) {
    return Math.log(iteration) / Math.log(iteration + 1) * temperature ;
  }
}
