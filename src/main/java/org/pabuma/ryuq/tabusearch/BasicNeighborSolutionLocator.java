package org.pabuma.ryuq.tabusearch;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;


public class BasicNeighborSolutionLocator implements BestNeighborSolutionLocator {

    @Override
    public Solutions findBestNeighbor(List<Solutions> neighborsSolutions, List<Solutions> solutionsInTabu) {
        CollectionUtils.filterInverse(neighborsSolutions, new Predicate<Solutions>() {
            @Override
            public boolean evaluate(Solutions neighbor) {
                return solutionsInTabu.contains(neighbor);
            }
        });


        Collections.sort(neighborsSolutions, new Comparator<Solutions>() {
            @Override
            public int compare(Solutions a, Solutions b) {
                return a.getValue().compareTo(b.getValue());
            }
        });

        return neighborsSolutions.get(0);
    }
}

