package org.pabuma.ryuq.iteratedlocalsearch;

import org.pabuma.ryuq.component.createinitialsolution.CreateInitialSolution;
import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;

import java.util.ArrayList;

public class IteratedLocalSearch<S extends Solution<?>> extends TrajectoryAlgorithm<S> {

    private S localSearchSolution;
    private MutationOperator<S> mutationOperator;

    public IteratedLocalSearch(Problem<S> problem,
                               MutationOperator<S> mutation,
                               CreateInitialSolution<S> createInitialSolution,
                               TerminationCondition terminationCriterion){
        super(problem, createInitialSolution, terminationCriterion);
        this.mutationOperator = mutation;
        this.localSearchSolution = LocalSearch(createInitialSolution.create(), 1);
    }

    @Override
    public S upgrade(S currentSolution) {
        S locMinSolution = perturbation(currentSolution, 4);
        problem.evaluate(locMinSolution);
        S localSearchSolution2 = LocalSearch(locMinSolution, 100);
        if (localSearchSolution2.objectives()[0] < currentSolution.objectives()[0]){
            currentSolution = localSearchSolution2;
        }
        return currentSolution;
    }

    public S LocalSearch(S currentSolution, int maxIterations){
        for(int i = 0; i < maxIterations; i ++) {
            S mutatedSolution = mutationOperator.execute((S) currentSolution.copy());
            problem.evaluate(mutatedSolution);
            if (mutatedSolution.objectives()[0] < currentSolution.objectives()[0]) {
                currentSolution = mutatedSolution;
            }
        }
        return currentSolution;
    }

    public S perturbation(S currentSolution, int n){
        for (int i = 0; i < n; i ++){
            S mutatedSolution = mutationOperator.execute((S) currentSolution.copy());
            currentSolution = mutatedSolution;
        }
        problem.evaluate(currentSolution);
        return currentSolution;
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
