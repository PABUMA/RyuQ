package org.pabuma.ryuq.component.terminationcondition.impl;

import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;

import java.util.Map;

/**
 * Class that allows to check the termination condition according to a maximum number of evaluations.
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
    Integer currentNumberOfEvaluations = (Integer) algorithmStatusData.get("EVALUATIONS") ;
    if (null == currentNumberOfEvaluations) {
      throw new RuntimeException("The EVALUATIONS field is null") ;
    }

    return (currentNumberOfEvaluations >= maximumNumberOfEvaluations) ;
  }
}
