package org.pabuma.ryuq.tabusearch;

import java.util.List;

public interface BestNeighborSolutionLocator {

    Solutions findBestNeighbor(List<Solutions> neighborsSolutions, List<Solutions> solutionsInTabu);

}