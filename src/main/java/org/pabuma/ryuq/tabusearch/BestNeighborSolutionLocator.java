package org.pabuma.ryuq.tabusearch;
import org.uma.jmetal.solution.Solution;

import java.util.List;

public interface BestNeighborSolutionLocator {

    Solution findBestNeighbor(List<Solution> neighborsSolutions, List<Solution> solutionsInTabu);

}