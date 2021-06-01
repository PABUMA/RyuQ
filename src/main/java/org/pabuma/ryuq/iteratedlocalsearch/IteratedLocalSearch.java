package org.pabuma.ryuq.iteratedlocalsearch;

import org.pabuma.ryuq.component.createinitialsolution.CreateInitialSolution;
import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;
import org.pabuma.ryuq.localsearch.LocalSearch;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;

import java.util.ArrayList;

public class IteratedLocalSearch<S extends Solution<?>> extends TrajectoryAlgorithm<S> {

    private S localSearchSolution;
    private MutationOperator<S> mutationOperator;
    private int countPerturbations;
    private int countLocalSearches;

    public IteratedLocalSearch(Problem<S> problem,
                               MutationOperator<S> mutation,
                               CreateInitialSolution<S> createInitialSolution,
                               TerminationCondition terminationCriterion){
        super(problem, createInitialSolution, terminationCriterion);
        this.mutationOperator = mutation;
        countPerturbations = 0;
        countLocalSearches = 0;
        this.localSearchSolution = LocalSearch(createInitialSolution.create(), 100000);
    }

    @Override
    public S upgrade(S currentSolution) {
        S localSearchSolution2 = LocalSearch(currentSolution, 100000);
        if (localSearchSolution2.objectives()[0] < currentSolution.objectives()[0]){
            currentSolution = localSearchSolution2;
        }
        S locMinSolution = perturbation(currentSolution, 4);
        problem.evaluate(locMinSolution);
        if (locMinSolution.objectives()[0] < currentSolution.objectives()[0]){
            currentSolution = locMinSolution;
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
            countLocalSearches++;
        }
        return currentSolution;
    }

    public S perturbation(S currentSolution, int n){
        for (int i = 0; i < n; i ++){
            S mutatedSolution = mutationOperator.execute((S) currentSolution.copy());
            currentSolution = mutatedSolution;
            countPerturbations++;
        }
        return currentSolution;
    }

    @Override
    protected void updateProgress() {
        evaluations += 100000;
        bestFoundSolution = updateBestFoundSolution(bestFoundSolution, currentSolution) ;

        attributes.put("EVALUATIONS", evaluations);
        attributes.put("BEST_SOLUTION", bestFoundSolution);
        attributes.put("COMPUTING_TIME", getCurrentComputingTime());

        observable.setChanged();
        observable.notifyObservers(attributes);
    }

    public int getTotalPert(){
        return countPerturbations;
    }

    public int getTotalLS(){
        return countLocalSearches;
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
