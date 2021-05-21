package org.pabuma.ryuq.msa.mutation;

import org.pabuma.ryuq.msa.MSASolution;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

public class RandomGapInsertion implements MutationOperator<MSASolution> {
  private final double mutationProbability;

  public RandomGapInsertion(double mutationProbability) {
    if ((mutationProbability < 0.0) || (mutationProbability > 1.0)) {
      throw new RuntimeException("Invalid mutation probability value: " + mutationProbability);
    }
    this.mutationProbability = mutationProbability;
  }

  @Override
  public double getMutationProbability() {
    return mutationProbability;
  }

  @Override
  public MSASolution execute(MSASolution solution) {
    if (null == solution) {
      throw new RuntimeException("Null solution");
    }

    if (JMetalRandom.getInstance().nextDouble() < mutationProbability) {
      solution.variables().forEach(sequence -> {
        int newGapPosition = JMetalRandom.getInstance().nextInt(0, sequence.length());
        sequence.insert(newGapPosition, "-");
      });
    }

    return solution;
  }
}