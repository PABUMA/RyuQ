package org.pabuma.ryuq.component.terminationcondition.impl;

import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;

import java.util.Map;

/**
 * Class that allows to check the termination condition when the computing time of an algorithm
 * gets higher than a given threshold. The {@link #isMet(Map)} method assumes that the map parameter has a {@link Long}
 * field called "COMPUTING_TIME".
 *
 *  @author Antonio J. Nebro <antonio@lcc.uma.es>
 */
public class TerminationByComputingTime implements TerminationCondition {
  private long maximumComputingTime;
  private int evaluations ;

  public TerminationByComputingTime(long maxComputingTime) {
    this.maximumComputingTime = maxComputingTime ;
    this.evaluations = 0 ;
  }

  @Override
  public boolean isMet(Map<String, Object> algorithmStatusData) {
    if (null ==  algorithmStatusData.get("COMPUTING_TIME")) {
      throw new RuntimeException("The COMPUTING_TIME field is null") ;
    }
    int currentComputingTime = (int) algorithmStatusData.get("COMPUTING_TIME") ;

    return currentComputingTime >= maximumComputingTime;
  }

  public int getEvaluations() {
    return evaluations ;
  }

  public long getMaximumComputingTime() {
    return maximumComputingTime;
  }
}
