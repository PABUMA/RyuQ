package org.pabuma.ryuq.localsearch;

import org.pabuma.ryuq.TrajectoryAlgorithm;
import org.pabuma.ryuq.component.createinitialsolution.CreateInitialSolution;
import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;
import org.pabuma.ryuq.simulatedannealing.cooling.CoolingScheme;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

/**
 * Class implementing a local search algorithm by extending the {@link TrajectoryAlgorithm} interface.
 *
 * @author Antonio J. Nebro
 * @param <S> Generic type of the problem solutions
 */
public class LocalSearch<S extends Solution<?>> extends TrajectoryAlgorithm<S> {
  private MutationOperator<S> mutationOperator;

  public LocalSearch(Problem<S> problem,
                     MutationOperator<S> mutation,
                     CreateInitialSolution<S> createInitialSolution,
                     TerminationCondition terminationCriterion) {
    super(problem, createInitialSolution, terminationCriterion) ;
    this.mutationOperator = mutation;
  }

  @Override
  public S upgrade(S currentSolution) {
    S mutatedSolution = mutationOperator.execute((S) currentSolution.copy());
    problem.evaluate(mutatedSolution);

    if (mutatedSolution.objectives()[0] < currentSolution.objectives()[0]) {
      currentSolution = mutatedSolution;
    }

    return currentSolution ;
  }

  @Override
  public String getName() {
    return "LS";
  }

  @Override
  public String getDescription() {
    return "Local Search";
  }
}