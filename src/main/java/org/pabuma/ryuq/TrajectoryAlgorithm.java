package org.pabuma.ryuq;

import org.pabuma.ryuq.component.algorithmstep.AlgorithmStep;
import org.pabuma.ryuq.component.createinitialsolution.CreateInitialSolution;
import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.observable.Observable;

import java.util.Map;

public class TrajectoryAlgorithm<S extends Solution<?>> implements Algorithm<S> {
  // Algorithm components
  private TerminationCondition terminationCondition;
  private CreateInitialSolution<S> initialSolutionGeneration;
  private AlgorithmStep<S> algorithmStep ;

  // State variables
  private long initTime;
  private long totalComputingTime;
  private int evaluations;
  private S bestFoundSolution;

  // Observable components
  private Observable<Map<String, Object>> observable;
  private Map<String, Object> attributes;


  public TrajectoryAlgorithm(CreateInitialSolution<S> createInitialSolution,
                             AlgorithmStep<S> algorithmStep,
                             TerminationCondition terminationCondition) {
    this.initialSolutionGeneration = createInitialSolution;
    this.terminationCondition = terminationCondition;
  }

  @Override
  public void run() {
    initTime = System.currentTimeMillis();
    S currentSolution = initialSolutionGeneration.create();
    initProgress();
    while (!terminationCondition.isMet(attributes)) {
      currentSolution = algorithmStep.upgrade(currentSolution) ;
      updateProgress();
    }
  }

  protected void initProgress() {
    evaluations = 1;

    attributes.put("EVALUATIONS", evaluations);
    attributes.put("BEST_FOUND_SOLUTION", bestFoundSolution);
    attributes.put("COMPUTING_TIME", getCurrentComputingTime());
  }

  protected void updateProgress() {
    evaluations++;

    attributes.put("EVALUATIONS", evaluations);
    attributes.put("BEST_FOUND_SOLUTION", bestFoundSolution);
    attributes.put("COMPUTING_TIME", getCurrentComputingTime());

    observable.setChanged();
    observable.notifyObservers(attributes);

    totalComputingTime = getCurrentComputingTime();
  }

  public long getCurrentComputingTime() {
    return System.currentTimeMillis() - initTime;
  }

  public long getTotalComputingTime() {
    return totalComputingTime;
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
