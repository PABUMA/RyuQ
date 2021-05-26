package org.pabuma.ryuq.component.terminationcondition.impl;

import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;

import java.util.Map;

/**
 * Class that allows to check the termination condition according to a maximum number of evaluations. The
 * {@link #isMet(Map)} method assumes that the map parameter has an {@link Integer} field called "EVALUATIONS".
 *
 *  @author Antonio J. Nebro <antonio@lcc.uma.es>
 */
public class TerminationByEvaluations implements TerminationCondition {
  private int maximumNumberOfEvaluations ;

  public TerminationByEvaluations(int maximumNumberOfEvaluations) {
    this.maximumNumberOfEvaluations = maximumNumberOfEvaluations ;
  }

  @Override
  public boolean isMet(Map<String, Object> algorithmStatusData) {
    if (null == algorithmStatusData.get("EVALUATIONS")) {
      throw new RuntimeException("The EVALUATIONS field is null") ;
    }

    int currentNumberOfEvaluations = (int) algorithmStatusData.get("EVALUATIONS") ;

    return (currentNumberOfEvaluations >= maximumNumberOfEvaluations) ;
  }

  public int getMaximumNumberOfEvaluations() {
    return maximumNumberOfEvaluations ;
  }
}
