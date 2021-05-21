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

/**
 * Abstract class representing the behaviour of a generic trajectory-based metaheuristic
 * @author Antonio J. Nebro
 *
 * @param <S> Generic yype of the problem solutions
 */
public abstract class TrajectoryAlgorithm<S extends Solution<?>> implements Algorithm<S> {
  protected Problem<S> problem ;

  // Algorithm components
  protected TerminationCondition terminationCondition;
  protected S initialSolution;

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
                             S initialSolution,
                             TerminationCondition terminationCondition) {
    this.problem = problem ;
    this.initialSolution = initialSolution;
    this.terminationCondition = terminationCondition;

    this.observable = new DefaultObservable<>("Trajectory-based Algorithm");
    this.attributes = new HashMap<>();
  }

  public TrajectoryAlgorithm(Problem<S> problem,
                             TerminationCondition terminationCondition) {
    this(problem, null, terminationCondition) ;
  }
  /*
  public TrajectoryAlgorithm(Problem<S> problem,
                             CreateInitialSolution<S> createInitialSolution,
                             TerminationCondition terminationCondition) {
    this.problem = problem ;
    this.initialSolutionGeneration = createInitialSolution;
    this.terminationCondition = terminationCondition;

    this.observable = new DefaultObservable<>("Trajectory-based Algorithm");
    this.attributes = new HashMap<>();
  }
  */
  public abstract S upgrade(S currentSolution) ;

  @Override
  public void run() {
    if (null == initialSolution) {
      throw new RuntimeException("The initial solution is null") ;
    }
    initTime = System.currentTimeMillis();
    currentSolution = initialSolution ;
    problem.evaluate(currentSolution) ;
    initProgress();
    while (!terminationCondition.isMet(attributes)) {
      currentSolution = upgrade(currentSolution) ;
      updateProgress();
    }
    totalComputingTime = getCurrentComputingTime();
  }

  /**
   * Method invoked just after the initialization of the algorithm and before the main loop of the algorithm
   */
  protected void initProgress() {
    evaluations = 1;

    bestFoundSolution = currentSolution ;

    attributes.put("EVALUATIONS", evaluations);
    attributes.put("BEST_SOLUTION", bestFoundSolution);
    attributes.put("COMPUTING_TIME", getCurrentComputingTime());

    observable.setChanged();
    observable.notifyObservers(attributes);
  }

  /**
   * Method invoked at the end of each algorithm iteration
   */
  protected void updateProgress() {
    evaluations++;
    bestFoundSolution = updateBestFoundSolution(bestFoundSolution, currentSolution) ;

    attributes.put("EVALUATIONS", evaluations);
    attributes.put("BEST_SOLUTION", bestFoundSolution);
    attributes.put("COMPUTING_TIME", getCurrentComputingTime());

    observable.setChanged();
    observable.notifyObservers(attributes);
  }

  protected S updateBestFoundSolution(S bestFoundSolution, S currentSolution) {
    if (currentSolution.objectives()[0] < bestFoundSolution.objectives()[0]) {
      return currentSolution ;
    } else {
      return bestFoundSolution ;
    }
  }

  public void setInitialSolution(S initialSolution) {
    this.initialSolution = initialSolution ;
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

  public int getEvaluations() {
    return evaluations ;
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
