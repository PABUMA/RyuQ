package org.pabuma.ryuq.localsearch;

import org.pabuma.ryuq.TrajectoryAlgorithm;
<<<<<<< HEAD
import org.pabuma.ryuq.component.createinitialsolution.CreateInitialSolution;
import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;
import org.pabuma.ryuq.simulatedannealing.cooling.CoolingScheme;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;
=======
import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
>>>>>>> simulatedannealing

/**
 * Class implementing a local search algorithm by extending the {@link TrajectoryAlgorithm} interface.
 *
 * @author Antonio J. Nebro
 * @param <S> Generic type of the problem solutions
 */
public class LocalSearch<S extends Solution<?>> extends TrajectoryAlgorithm<S> {
  private MutationOperator<S> mutationOperator;
<<<<<<< HEAD

  public LocalSearch(Problem<S> problem,
                     MutationOperator<S> mutation,
                     CreateInitialSolution<S> createInitialSolution,
                     TerminationCondition terminationCriterion) {
    super(problem, createInitialSolution, terminationCriterion) ;
    this.mutationOperator = mutation;
=======
  private int numberOfIterationsWithoutImprovement ;

  public LocalSearch(Problem<S> problem,
                     MutationOperator<S> mutation,
                     S initialSolution,
                     TerminationCondition terminationCriterion) {
    super(problem, initialSolution, terminationCriterion) ;
    this.mutationOperator = mutation;
    this.numberOfIterationsWithoutImprovement = 0 ;
  }

  public LocalSearch(Problem<S> problem,
                     MutationOperator<S> mutation,
                     TerminationCondition terminationCriterion) {
    this(problem, mutation, null, terminationCriterion) ;
>>>>>>> simulatedannealing
  }

  @Override
  public S upgrade(S currentSolution) {
    S mutatedSolution = mutationOperator.execute((S) currentSolution.copy());
    problem.evaluate(mutatedSolution);

    if (mutatedSolution.objectives()[0] < currentSolution.objectives()[0]) {
      currentSolution = mutatedSolution;
<<<<<<< HEAD
=======
      numberOfIterationsWithoutImprovement = 0 ;
    } else {
      numberOfIterationsWithoutImprovement ++ ;
>>>>>>> simulatedannealing
    }

    return currentSolution ;
  }

  @Override
<<<<<<< HEAD
=======
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
>>>>>>> simulatedannealing
  public String getName() {
    return "LS";
  }

  @Override
  public String getDescription() {
    return "Local Search";
  }
}