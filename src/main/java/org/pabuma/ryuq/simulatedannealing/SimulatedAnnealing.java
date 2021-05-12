package org.pabuma.ryuq.simulatedannealing;

import org.pabuma.ryuq.simulatedannealing.cooling.CoolingScheme;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.experimental.componentbasedalgorithm.catalogue.termination.Termination;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.observable.Observable;
import org.uma.jmetal.util.observable.impl.DefaultObservable;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import java.util.HashMap;
import java.util.Map;

public class SimulatedAnnealing<S extends Solution<?>> implements Algorithm<S> {
  private S bestFoundSolution;
  private S currentSolution;
  private double temperature;
  private Problem<S> problem;

  private MutationOperator<S> mutationOperator;

  private Map<String, Object> attributes;

  private long initTime;
  private long totalComputingTime;
  private int evaluations;
  private Observable<Map<String, Object>> observable;

  private CoolingScheme coolingScheme;

  private Termination termination;

  private double minimumTemperature = 0.000001;

  public SimulatedAnnealing(Problem<S> problem, MutationOperator<S> mutation,
                            Termination terminationCriterion, double initialTemperature, CoolingScheme coolingScheme) {
    this.problem = problem;
    this.mutationOperator = mutation;
    this.temperature = initialTemperature;
    bestFoundSolution = null;
    currentSolution = null;
    this.termination = terminationCriterion;
    this.coolingScheme = coolingScheme;
    this.observable = new DefaultObservable<>("Simulated Annealing");
    this.attributes = new HashMap<>();
  }

  @Override
  public void run() {
    currentSolution = problem.createSolution();
    problem.evaluate(currentSolution);
    evaluations = 1;
    bestFoundSolution = currentSolution;

    attributes.put("EVALUATIONS", evaluations);
    attributes.put("BEST_SOLUTION", bestFoundSolution);
    attributes.put("CURRENT_SOLUTION", currentSolution);

    while (!termination.isMet(attributes)) {
      S mutatedSolution = mutationOperator.execute((S) currentSolution.copy());
      problem.evaluate(mutatedSolution);

      evaluations++;

      if (mutatedSolution.objectives()[0] < currentSolution.objectives()[0]) {
        currentSolution = mutatedSolution;
      } else {
        double acceptanceProbability = compute_acceptance_probability(currentSolution, mutatedSolution, temperature);

        if (acceptanceProbability > JMetalRandom.getInstance().nextDouble()) {
          currentSolution = mutatedSolution;
        }
      }

      bestFoundSolution = currentSolution;
      temperature = coolingScheme.updateTemperature(temperature);

      attributes.put("EVALUATIONS", evaluations);
      attributes.put("BEST_SOLUTION", bestFoundSolution);
      attributes.put("CURRENT_SOLUTION", currentSolution);

      observable.setChanged();
      observable.notifyObservers(attributes);
    }
  }

  private double compute_acceptance_probability(S currentSolution, S mutatedSolution, double temperature) {
    double value = (mutatedSolution.objectives()[0] - currentSolution.objectives()[0])
            / Math.max(temperature, minimumTemperature);
    return Math.exp(-1.0 * value);
  }

  @Override
  public S getResult() {
    return bestFoundSolution;
  }

  @Override
  public String getName() {
    return "SA";
  }

  @Override
  public String getDescription() {
    return "Simulated Annealing";
  }

  public Observable<Map<String, Object>> getObservable() {
    return observable;
  }
}