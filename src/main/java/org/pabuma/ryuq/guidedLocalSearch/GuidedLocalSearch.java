package org.pabuma.ryuq.guidedLocalSearch;

import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;
import org.pabuma.ryuq.msa.MSASolution;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.solution.Solution;

import org.pabuma.ryuq.TrajectoryAlgorithm;
import org.pabuma.ryuq.component.createinitialsolution.CreateInitialSolution;
import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

public class GuidedLocalSearch<S extends Solution<?>> extends TrajectoryAlgorithm<S> {

    private MutationOperator<S> mutationOperator;
    private int numberOfIterationsWithoutImprovement;
    private int lambda;
    int iteraciones;

    public GuidedLocalSearch(Problem<S> problem,
                             MutationOperator<S> mutation,
                             S initialSolution,
                             TerminationCondition terminationCriterion,int iteraciones) {

        super(problem, initialSolution, terminationCriterion);
        this.mutationOperator = mutation;
        this.iteraciones=iteraciones;
    }


    @Override //local search with restart
    public S upgrade(S currentSolution) {
        currentSolution= problem.createSolution();
        for ( int i = 0;
        i<iteraciones;
        i++){
            S mutatedSolution = mutationOperator.execute((S) currentSolution.copy());
            problem.evaluate(mutatedSolution);

            if (mutatedSolution.objectives()[0] < currentSolution.objectives()[0]) {
                currentSolution = mutatedSolution;
                numberOfIterationsWithoutImprovement = 0;
            } else {
                numberOfIterationsWithoutImprovement++;
            }

        }

        return currentSolution;
    }

    @Override
    public String getName() {
        return "GLS";
    }

    @Override
    public String getDescription() {
        return "Guided Local Search ";
    }

    @Override
    protected void updateProgress() {
        evaluations+=100000;
        bestFoundSolution = updateBestFoundSolution(bestFoundSolution, currentSolution) ;

        attributes.put("EVALUATIONS", evaluations);
        attributes.put("BEST_SOLUTION", bestFoundSolution);
        attributes.put("COMPUTING_TIME", getCurrentComputingTime());

        observable.setChanged();
        observable.notifyObservers(attributes);
    }


}
