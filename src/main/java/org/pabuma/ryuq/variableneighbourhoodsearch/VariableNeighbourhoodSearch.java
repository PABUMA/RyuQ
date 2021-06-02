package org.pabuma.ryuq.variableneighbourhoodsearch;

import org.pabuma.ryuq.TrajectoryAlgorithm;
import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;
import org.pabuma.ryuq.localsearch.LocalSearch;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class implementing a Variable Neighbourhood Search algorithm by extending the {@link
 * org.pabuma.ryuq.TrajectoryAlgorithm} interface.
 *
 * @author Carmen Lucía Arrabalí Cañete, Talal Awija, Oleg Brezitskyy, Sergio Martín Vera
 * @param <S> Generic type of the problem solutions
 */
public class VariableNeighbourhoodSearch<S extends Solution<?>> extends TrajectoryAlgorithm<S> {
  int k_max;
  int itemsPerNeighbourhood;
  int localSearchEvaluations;
  MutationOperator<S> mo;

  public VariableNeighbourhoodSearch(
      Problem<S> problem,
      S initialSolution,
      TerminationCondition terminationCondition,
      MutationOperator<S> mo,
      int k_max,
      int itemsPerNeighbourhood,
      int localSearchEvaluations) {
    super(problem, initialSolution, terminationCondition);
    this.k_max = k_max;
    this.itemsPerNeighbourhood = itemsPerNeighbourhood;
    this.localSearchEvaluations = localSearchEvaluations;
    this.mo = mo;
  }

  @Override
  public S upgrade(S currentSolution) {
    int k = 1;
    ArrayList<ArrayList<S>> neighbourhoods = generateNeighbourhoods(currentSolution);
    while (k <= k_max && !terminationCondition.isMet(attributes)) {
      // Step 1: Shaking
      S mutatedSolution = shake(neighbourhoods, k);
      problem.evaluate(mutatedSolution);

      // Step 2: Local search
      LocalSearch<S> ls =
          new LocalSearch<>(problem, mo, mutatedSolution, new TerminationByEvaluations(this.localSearchEvaluations));
      ls.run();
      S mutatedSolution2 = ls.getResult();

      // Step 3: Neighbourhood change (move)
      if (mutatedSolution2.objectives()[0] < currentSolution.objectives()[0]) {
        currentSolution = mutatedSolution2;
        break;
      } else {
        k++;
      }
    }
    return currentSolution;
  }

  /**
   * Generates an ArrayList of k-neighbourhoods ArrayLists containing each k-neighbourhood items.
   *
   * @param currentSolution: solution from which to generate the neighborhoods.
   * @return Array list containing ArrayLists with neighbourhood items.
   */
  public ArrayList generateNeighbourhoods(S currentSolution) {
    ArrayList<ArrayList<S>> neighbourhoods = new ArrayList<ArrayList<S>>();

    // For each k-neighbourhood
    for (int k = 1; k <= this.k_max; k++) {
      ArrayList<S> kNeighbourhood = new ArrayList<S>();
      // For each item in k-neighbourhood
      for (int item = 0; item < this.itemsPerNeighbourhood; item++) {
        // Apply k mutations of distance 1 for each item
        S mutatedSolution = (S) currentSolution.copy();
        for (int i = 1; i <= k; i++) {
          mutatedSolution = this.mo.execute(mutatedSolution);
        }
        kNeighbourhood.add(mutatedSolution);
      }
      neighbourhoods.add(kNeighbourhood);
    }
    return neighbourhoods;
  }

  /**
   * Shake function generates a point y randomly from the k-th neighbourhood of x
   *
   * @param kNeigbourhoods: pre-generated list of kNeighbourhoods.
   * @param k: k-neighborhood from which to obtain the random point.
   * @return random point of the selected k-nearest neighborhood.
   */
  public S shake(ArrayList<ArrayList<S>> kNeigbourhoods, int k) {
    Random rand = new Random();
    ArrayList<S> kNeigbourhood = kNeigbourhoods.get(k - 1);
    return kNeigbourhood.get(rand.nextInt(kNeigbourhood.size()));
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
