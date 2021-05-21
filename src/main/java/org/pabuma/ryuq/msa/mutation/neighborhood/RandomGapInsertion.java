package org.pabuma.ryuq.msa.neighborhood;

import org.pabuma.ryuq.msa.MSASolution;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

public class RandomGapInsertion implements MutationOperator<MSASolution> {
  private double mutationProbability;

  public RandomGapInsertion(double mutationProbability) {
    if ((mutationProbability < 0.0) || (mutationProbability > 1.0)) {
      throw new RuntimeException("Invalid mutation probability value: " + mutationProbability) ;
    }
    this.mutationProbability = mutationProbability ;
  }
  @Override
  public double getMutationProbability() {
    return mutationProbability;
  }

  @Override
  public MSASolution execute(MSASolution solution) {
    if (null == solution) {
      throw new RuntimeException("Null solution") ;
    }

    for (var i = 0; i  < solution.variables().size(); i++) {
      if (JMetalRandom.getInstance().nextDouble() < mutationProbability) {
        StringBuilder currentSequence = solution.variables().get(i) ;
        int randomPosition = JMetalRandom.getInstance().nextInt(0, currentSequence.length());
        currentSequence.insert(randomPosition, "-");
      }
    }
    return solution ;
  }
}
