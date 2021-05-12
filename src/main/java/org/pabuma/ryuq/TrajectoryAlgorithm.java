package org.pabuma.ryuq;

import org.pabuma.ryuq.component.createinitialsolution.CreateInitialSolution;
import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.observable.Observable;
import org.uma.jmetal.util.observable.impl.DefaultObservable;

import java.util.HashMap;
import java.util.Map;

public abstract class TrajectoryAlgorithm<S extends Solution<?>> implements Algorithm<S> {
  protected Problem<S> problem ;

  // Algorithm components
  protected TerminationCondition terminationCondition;
  protected CreateInitialSolution<S> initialSolutionGeneration;

  // State variables
  protected long initTime;
  protected long totalComputingTime;
  protected int evaluations;
  protected S bestFoundSolution;
  protected S currentSolution ;

  // Observable components
  protected Observable<Map<String, Object>> observable;
  protected Map<String, Object> attributes;

  public TrajectoryAlgorithm(Problem<S> problem,
                             CreateInitialSolution<S> createInitialSolution,
                             TerminationCondition terminationCondition) {
    this.problem = problem ;
    this.initialSolutionGeneration = createInitialSolution;
    this.terminationCondition = terminationCondition;

    this.observable = new DefaultObservable<>("Trajectory-based Algorithm");
    this.attributes = new HashMap<>();
  }

  public abstract S upgrade(S currentSolution) ;

  @Override
  public void run() {
    initTime = System.currentTimeMillis();
    currentSolution = initialSolutionGeneration.create();
    problem.evaluate(currentSolution) ;
    initProgress();
    while (!terminationCondition.isMet(attributes)) {
      currentSolution = upgrade(currentSolution) ;
      updateProgress();
    }
    totalComputingTime = getCurrentComputingTime();
  }

  protected void initProgress() {
    evaluations = 1;

    bestFoundSolution = currentSolution ;

    attributes.put("EVALUATIONS", evaluations);
    attributes.put("BEST_SOLUTION", bestFoundSolution);
    attributes.put("COMPUTING_TIME", getCurrentComputingTime());

    observable.setChanged();
    observable.notifyObservers(attributes);
  }

  protected void updateProgress() {
    evaluations++;
    if (currentSolution.objectives()[0] < bestFoundSolution.objectives()[0]) {
      bestFoundSolution = currentSolution ;
    }

    attributes.put("EVALUATIONS", evaluations);
    attributes.put("BEST_SOLUTION", bestFoundSolution);
    attributes.put("COMPUTING_TIME", getCurrentComputingTime());

    observable.setChanged();
    observable.notifyObservers(attributes);
  }

  public long getCurrentComputingTime() {
    return System.currentTimeMillis() - initTime;
  }

  public long getTotalComputingTime() {
    return totalComputingTime;
  }

  public Observable<Map<String, Object>> getObservable() {
    return observable ;
  }

  @Override
  public S getResult() {
    return bestFoundSolution;
  }

  @Override
  public String getName() {
    return "Trajectory-based metaheuristic";
  }

  @Override
  public String getDescription() {
    return "Trajectory-based metaheuristic";
  }
}
