package org.pabuma.ryuq.tabusearch;

import org.uma.jmetal.solution.Solution;

public interface TabuList extends Iterable<Solution> {

    void add(Solution solution);

    Boolean contains(Solution solution);

    void updateSize(Integer currentIteration, Solution bestSolutionFound);

}