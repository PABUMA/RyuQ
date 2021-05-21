package org.pabuma.ryuq.component.terminationcondition.impl;

import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;

import java.util.Map;

/**
 * Class that allows to check the termination condition depending whether a consecutive number of iterations
 * without improvements in the search has been reached. The {@link #isMet(Map)} method assumes that the map parameter
 * has an {@link Integer} field called "NUMBER_OF_ITERATIONS_WITHOUT_IMPROVEMENT".
 *
 *  @author Antonio J. Nebro <antonio@lcc.uma.es>
 */
public class TerminationByLimitOfIterationsWithoutImprovement implements TerminationCondition {
  private int maximumNumberOfIterationsWithoutImprovement ;

  public TerminationByLimitOfIterationsWithoutImprovement(int maximumNumberOfIterationsWithoutImprovement) {
    this.maximumNumberOfIterationsWithoutImprovement = maximumNumberOfIterationsWithoutImprovement ;
  }

  @Override
  public boolean isMet(Map<String, Object> algorithmStatusData) {
    Integer numberOfIterationsWithoutImprovement = (Integer) algorithmStatusData.get("NUMBER_OF_ITERATIONS_WITHOUT_IMPROVEMENT") ;
    if (null == numberOfIterationsWithoutImprovement) {
      throw new RuntimeException("The NUMBER_OF_ITERATIONS_WITHOUT_IMPROVEMENT field is null") ;
    }

    return (numberOfIterationsWithoutImprovement >= maximumNumberOfIterationsWithoutImprovement) ;
  }
}
