package org.pabuma.ryuq.component.createinitialsolution.impl;

import org.pabuma.ryuq.component.createinitialsolution.CreateInitialSolution;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;

/**
 * @param <S>
 */
public class DefaultSolutionCreation<S extends Solution<?>> implements CreateInitialSolution<S> {
  private Problem<S> problem;

  public DefaultSolutionCreation(Problem<S> problem) {
    this.problem = problem;
  }

  public S create() {
    return problem.createSolution();
  }
}
