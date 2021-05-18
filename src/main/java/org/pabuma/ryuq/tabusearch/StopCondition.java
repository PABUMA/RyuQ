package org.pabuma.ryuq.tabusearch;

import org.uma.jmetal.solution.Solution;

public interface StopCondition {

    Boolean mustStop(Integer currentIteration, Solution bestSolutionFound);

}