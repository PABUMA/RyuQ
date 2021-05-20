package org.pabuma.ryuq.iteratedlocalsearch;

import org.pabuma.ryuq.TrajectoryAlgorithm;
import org.pabuma.ryuq.component.createinitialsolution.CreateInitialSolution;
import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;

import java.util.ArrayList;

public class IteratedLocalSearch<S extends Solution<?>> extends TrajectoryAlgorithm<S> {

    private ArrayList<S> history;
    private S localSearchSolution;

    public IteratedLocalSearch(Problem<S> problem,
                               CreateInitialSolution<S> createInitialSolution,
                               TerminationCondition terminationCriterion){
        super(problem, createInitialSolution, terminationCriterion);
        this.localSearchSolution = LocalSearch(createInitialSolution.create());
    }

    @Override
    public S upgrade(S currentSolution) {
        // currentSolution = localSearchSolution;
        S locMinSolution = perturbation(currentSolution, history);
        problem.evaluate(locMinSolution);
        S localSearchSolution2 = LocalSearch(locMinSolution);
        if (localSearchSolution2.objectives()[0] < currentSolution.objectives()[0]){
            currentSolution = localSearchSolution2;
        }
        return currentSolution;
    }

    public S LocalSearch(S currentSolution){
       /* if (x < currentSolution.objectives()[0]) {
        *   currentSolution = x;
        * }
        */
        return currentSolution ;
    }

    public S perturbation(S currentSolution, ArrayList<S> history){
        return null;
    }

    @Override
    public String getName() {
        return "ILS";
    }

    @Override
    public String getDescription() {
        return "Iterated local search";
    }
}
