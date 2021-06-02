package org.pabuma.ryuq.tabusearch;

import org.pabuma.ryuq.TrajectoryAlgorithm;
import org.pabuma.ryuq.component.createinitialsolution.CreateInitialSolution;
import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;

import java.util.*;

/**
 * Class implementing a local search algorithm by extending the {@link TrajectoryAlgorithm} interface.
 *
 * @author Antonio J. Nebro
 * @param <S> Generic type of the problem solutions
 */
public class TabuSearch<S extends Solution<?>> extends TrajectoryAlgorithm<S> {
    private MutationOperator<S> mutationOperator;
    private TabuList<S> tabuList;

    public TabuSearch(Problem<S> problem,
                      MutationOperator<S> mutation,
                      CreateInitialSolution<S> createInitialSolution,
                      TerminationCondition terminationCriterion) {
        super(problem, createInitialSolution, terminationCriterion) ;
        this.mutationOperator = mutation;
        this.tabuList = new TabuList<>(1000);
    }

    public TabuSearch(Problem<S> problem,
                      MutationOperator<S> mutation,
                      TerminationCondition terminationCriterion) {
        this(problem, mutation, null, terminationCriterion) ;
        this.tabuList = new TabuList<>(1000);
    }

    public S upgrade(S currentSolution) {
        S localSolution = mutationOperator.execute((S) currentSolution.copy());
        if (!tabuList.inIn(localSolution)) {
            problem.evaluate(localSolution);
            updateProgress(localSolution);
        }

        if (localSolution.objectives()[0] < currentSolution.objectives()[0]) {
            currentSolution = localSolution;
        } else {
            tabuList.addList(localSolution);
        }

        return currentSolution ;
    }

    @Override
    public void initProgress() {
        super.initProgress();
    }

    public void updateProgress(S solution) {
        evaluations++;

        bestFoundSolution = currentSolution ;

        attributes.put("EVALUATIONS", evaluations);
        attributes.put("BEST_SOLUTION", bestFoundSolution);
        attributes.put("COMPUTING_TIME", getCurrentComputingTime());

        observable.setChanged();
        observable.notifyObservers(attributes);
    }

    @Override
    public String getName() {
        return "TS";
    }

    @Override
    public String getDescription() {
        return "Tabu Search";
    }
}
