package org.pabuma.ryuq.simulatedannealing.cooling.impl;

import org.pabuma.ryuq.simulatedannealing.cooling.CoolingScheme;

public class Geometric implements CoolingScheme {
  private double alpha ;

  public Geometric(double alpha) {
    this.alpha = alpha ;
  }

  @Override
  public double updateTemperature(double temperature) {
    return alpha * temperature ;
  }
}
