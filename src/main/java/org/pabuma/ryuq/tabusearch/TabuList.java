package org.pabuma.ryuq.tabusearch;

import org.uma.jmetal.solution.Solution;

public interface TabuList extends Iterable<Solutions> {

    void add(Solutions solution);

    Boolean contains(Solutions solution);

    void updateSize(Integer currentIteration, Solutions bestSolutionFound);

}