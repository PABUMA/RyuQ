package org.pabuma.ryuq.msa.neighborhood;

import org.pabuma.ryuq.msa.MSASolution;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class RandomGapInsertion implements MutationOperator<MSASolution> {
  private double mutationProbability;

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

    //for (int i = 0; i  < solution.variables().size(); i++) {
    //  if (JMetalRandom.getInstance().nextDouble() < mutationProbability) {
    //    StringBuilder sequence = solution.variables().get(i) ;
    //    int randomPosition = JMetalRandom.getInstance().nextInt(0, sequence.length());
    //    sequence.insert(randomPosition, "-");
    //}
    //}

    IntStream
            .range(0, solution.variables().size())
            .filter(i -> JMetalRandom.getInstance().nextDouble() < mutationProbability)
            .mapToObj(i -> solution.variables().get(i))
            .forEach(sequence ->
                    sequence.insert(JMetalRandom.getInstance().nextInt(0, sequence.length()), "-"));

    return solution;
  }

}
