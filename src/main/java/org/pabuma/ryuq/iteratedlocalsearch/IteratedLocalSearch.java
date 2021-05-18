package org.pabuma.ryuq.iteratedlocalsearch;

import org.pabuma.ryuq.TrajectoryAlgorithm;
import org.pabuma.ryuq.component.createinitialsolution.CreateInitialSolution;
import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;

public class IteratedLocalSearch<S extends Solution<?>> extends TrajectoryAlgorithm<S> {

    public IteratedLocalSearch(Problem<S> problem, CreateInitialSolution<S> createInitialSolution,
                               TerminationCondition terminationCriterion){
        super(problem, createInitialSolution, terminationCriterion);

    }

    @Override
    public S upgrade(S currentSolution) {
        return null;
    }

    @Override
    public String getName() {
        return "VNS";
    }

    @Override
    public String getDescription() {
        return "Variable Neighbourhood Search";
    }
}
