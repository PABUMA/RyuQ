package org.pabuma.ryuq.simulatedannealing.cooling.impl;

import org.pabuma.ryuq.simulatedannealing.cooling.CoolingScheme;

public class Linear implements CoolingScheme {
  private double beta ;

  public Linear(double beta) {
    this.beta = beta ;
  }

  @Override
  public double updateTemperature(double temperature) {
    return temperature - beta ;
  }
}
