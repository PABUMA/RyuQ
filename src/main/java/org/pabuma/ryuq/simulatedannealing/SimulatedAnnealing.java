package org.pabuma.ryuq.simulatedannealing;

import org.pabuma.ryuq.TrajectoryAlgorithm;
import org.pabuma.ryuq.component.createinitialsolution.CreateInitialSolution;
import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;
import org.pabuma.ryuq.simulatedannealing.cooling.CoolingScheme;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

/**
 * Class implementing a simulated annealing algorithm by extending the {@link TrajectoryAlgorithm} interface.
 *
 * @author Antonio J. Nebro
 * @param <S> Generic type of the problem solutions
 */
public class SimulatedAnnealing<S extends Solution<?>> extends TrajectoryAlgorithm<S> {
  private static final double minimumTemperature = 0.000001;
  private double temperature;

  private MutationOperator<S> mutationOperator;
  private CoolingScheme coolingScheme;

  public SimulatedAnnealing(Problem<S> problem,
                            MutationOperator<S> mutation,
                            S initialSolution,
                            TerminationCondition terminationCriterion,
                            double initialTemperature,
                            CoolingScheme coolingScheme) {
    super(problem, initialSolution, terminationCriterion) ;
    this.mutationOperator = mutation;
    this.temperature = initialTemperature;
    this.coolingScheme = coolingScheme;
  }

  @Override
  public S upgrade(S currentSolution) {
    S mutatedSolution = mutationOperator.execute((S) currentSolution.copy());
    problem.evaluate(mutatedSolution);

    if (mutatedSolution.objectives()[0] < currentSolution.objectives()[0]) {
      currentSolution = mutatedSolution;
    } else {
      double acceptanceProbability = compute_acceptance_probability(currentSolution, mutatedSolution, temperature);

      if (acceptanceProbability > JMetalRandom.getInstance().nextDouble()) {
        currentSolution = mutatedSolution;
      }
    }

    temperature = coolingScheme.updateTemperature(temperature, evaluations);

    return currentSolution ;
  }

  protected double compute_acceptance_probability(S currentSolution, S mutatedSolution, double temperature) {
    double value = (mutatedSolution.objectives()[0] - currentSolution.objectives()[0])
            / Math.max(temperature, minimumTemperature);
    return Math.exp(-1.0 * value);
  }

  @Override
  public String getName() {
    return "SA";
  }

  @Override
  public String getDescription() {
    return "Simulated Annealing";
  }
}