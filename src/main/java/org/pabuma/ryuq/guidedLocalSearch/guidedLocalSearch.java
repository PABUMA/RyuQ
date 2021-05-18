package org.pabuma.ryuq.guidedLocalSearch;

import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.solution.Solution;

import org.pabuma.ryuq.TrajectoryAlgorithm;
import org.pabuma.ryuq.component.createinitialsolution.CreateInitialSolution;
import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

public class guidedLocalSearch <S extends Solution<?>> extends TrajectoryAlgorithm<S> {

    private MutationOperator<S> mutationOperator;

  public guidedLocalSearch(Problem<S> problem,
                MutationOperator<S> mutation,
                CreateInitialSolution<S> createInitialSolution,
                TerminationCondition terminationCriterion) {
            super(problem, createInitialSolution, terminationCriterion) ;
            this.mutationOperator = mutation;
        }

    @Override
    public S upgrade(S currentSolution) {
        return null;
    }
    @Override
    public String getName() {
        return "GLS";
    }

    @Override
    public String getDescription() {
        return "Guided Local Search ";
    }
}
