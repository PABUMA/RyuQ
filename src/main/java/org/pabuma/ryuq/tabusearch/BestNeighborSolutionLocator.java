package org.pabuma.ryuq.tabusearch;
import org.uma.jmetal.solution.Solution;

import java.util.List;

public interface BestNeighborSolutionLocator {

    Solutions findBestNeighbor(List<Solutions> neighborsSolutions, List<Solutions> solutionsInTabu);

}