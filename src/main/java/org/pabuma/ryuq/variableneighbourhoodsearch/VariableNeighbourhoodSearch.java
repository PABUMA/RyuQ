package org.pabuma.ryuq.variableneighbourhoodsearch;

import org.pabuma.ryuq.TrajectoryAlgorithm;
import org.pabuma.ryuq.component.createinitialsolution.CreateInitialSolution;
import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;

/**
 * Class implementing a Variable Neighbourhood Search algorithm by extending the {@link
 * org.pabuma.ryuq.TrajectoryAlgorithm} interface.
 *
 * @author Carmen Lucía Arrabalí Cañete, Talal Awija, Oleg Brezitskyy, Sergio Martín Vera
 * @param <S> Generic type of the problem solutions
 */
public class VariableNeighbourhoodSearch<S extends Solution<?>> extends TrajectoryAlgorithm<S> {
  int k_max;
  public VariableNeighbourhoodSearch(
      Problem<S> problem,
      CreateInitialSolution<S> createInitialSolution,
      TerminationCondition terminationCondition,
      int k_max) {
    super(problem, createInitialSolution, terminationCondition);
    this.k_max = k_max;
  }

  @Override
  public S upgrade(S currentSolution) {
    int k = 1;
    while (k <= k_max) {
      // Step 1: Shaking
      S mutatedSolution = shake(currentSolution, k);
      problem.evaluate(mutatedSolution);

      // Step 2: Local search
      S mutatedSolution2 = null;

      // Step 3: Neighbourhood change (move)
      if (mutatedSolution2.objectives()[0] < currentSolution.objectives()[0]) {
        currentSolution = mutatedSolution2;
        k = 1;
      } else {
        k++;
      }
    }
    return currentSolution;
  }

  /**
   * Shake function generates a point y randomly from the k-th neighbourhood of x
   * @param solution
   * @param k
   * @return
   */
  public S shake(S solution, int k) {
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
