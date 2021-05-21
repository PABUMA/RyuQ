package org.pabuma.ryuq.localsearch;

import org.pabuma.ryuq.TrajectoryAlgorithm;
import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;

/**
 * Class implementing a local search algorithm by extending the {@link TrajectoryAlgorithm} interface.
 *
 * @author Antonio J. Nebro
 * @param <S> Generic type of the problem solutions
 */
public class LocalSearch<S extends Solution<?>> extends TrajectoryAlgorithm<S> {
  private MutationOperator<S> mutationOperator;
  private int numberOfIterationsWithoutImprovement ;

  public LocalSearch(Problem<S> problem,
                     MutationOperator<S> mutation,
                     S initialSolution,
                     TerminationCondition terminationCriterion) {
    super(problem, initialSolution, terminationCriterion) ;
    this.mutationOperator = mutation;
    this.numberOfIterationsWithoutImprovement = 0 ;
  }

  @Override
  public S upgrade(S currentSolution) {
    S mutatedSolution = mutationOperator.execute((S) currentSolution.copy());
    problem.evaluate(mutatedSolution);

    if (mutatedSolution.objectives()[0] < currentSolution.objectives()[0]) {
      currentSolution = mutatedSolution;
      numberOfIterationsWithoutImprovement = 0 ;
    } else {
      numberOfIterationsWithoutImprovement ++ ;
    }

    return currentSolution ;
  }

  @Override
  public void initProgress() {
    attributes.put("NUMBER_OF_ITERATIONS_WITHOUT_IMPROVEMENT", numberOfIterationsWithoutImprovement);
    super.initProgress();
  }

  @Override
  public void updateProgress() {
    attributes.put("NUMBER_OF_ITERATIONS_WITHOUT_IMPROVEMENT", numberOfIterationsWithoutImprovement);
    super.updateProgress();
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