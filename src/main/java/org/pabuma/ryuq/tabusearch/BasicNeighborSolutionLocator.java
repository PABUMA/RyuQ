package org.pabuma.ryuq.tabusearch;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

   
public class BasicNeighborSolutionLocator implements BestNeighborSolutionLocator {

     @Override
     public Solution findBestNeighbor(List<Solution> neighborsSolutions, final List<Solution> solutionsInTabu) {
 
         CollectionUtils.filterInverse(neighborsSolutions, new Predicate<Solution>() {
             @Override
             public boolean evaluate(Solution neighbor) {
                 return solutionsInTabu.contains(neighbor);
             }
         });


         Collections.sort(neighborsSolutions, new Comparator<Solution>() {
             @Override
             public int compare(Solution a, Solution b) {
                 return a.getValue().compareTo(b.getValue());
             }
         });
            
         return neighborsSolutions.get(0);
     }

    }
}
