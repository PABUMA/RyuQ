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
  public VariableNeighbourhoodSearch(
      Problem<S> problem,
      CreateInitialSolution<S> createInitialSolution,
      TerminationCondition terminationCondition) {
    super(problem, createInitialSolution, terminationCondition);
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
