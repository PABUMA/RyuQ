package org.pabuma.ryuq.tabusearch;


public interface StopCondition {

    Boolean mustStop(Integer currentIteration, Solutions bestSolutionFound);

}