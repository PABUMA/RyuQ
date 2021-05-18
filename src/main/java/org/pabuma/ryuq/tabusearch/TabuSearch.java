package org.pabuma.ryuq.tabusearch;

import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.pabuma.ryuq.tabusearch.Solutions;

public class TabuSearch {

    private TabuList tabuList;
    private StopCondition stopCondition;
    private BestNeighborSolutionLocator solutionLocator;

    public TabuSearch(TabuList tabuList, StopCondition stopCondition, BestNeighborSolutionLocator solutionLocator) {
        this.tabuList = tabuList;
        this.stopCondition = stopCondition;
        this.solutionLocator = solutionLocator;
    }

    public Solutions run(Solutions initialSolution) {
        Solutions bestSolution = initialSolution;
        Solutions currentSolution = initialSolution;

        Integer currentIteration = 0;
        while (!stopCondition.mustStop(++currentIteration, bestSolution)) {

            List<Solutions> candidateNeighbors = currentSolution.getNeighbors();
            List<Solutions> solutionsInTabu = (List<Solutions>) IteratorUtils.toList(tabuList.iterator());

            Solutions bestNeighborFound = (Solutions) solutionLocator.findBestNeighbor(candidateNeighbors, solutionsInTabu);
            if (bestNeighborFound.getValue() < bestSolution.getValue()) {
                bestSolution = bestNeighborFound;
            }

            tabuList.add(currentSolution);
            currentSolution = bestNeighborFound;

            tabuList.updateSize(currentIteration, bestSolution);
        }

        return bestSolution;
    }

}
