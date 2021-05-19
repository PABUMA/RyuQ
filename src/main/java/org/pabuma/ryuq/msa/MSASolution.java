package org.pabuma.ryuq.msa;

import org.uma.jmetal.solution.AbstractSolution;
import org.uma.jmetal.solution.Solution;

import java.util.HashMap;
import java.util.List;

/**
 * Class representing a multiple sequence alignment (MSA). Each sequence is stored as a {@link StringBuilder}
 * object. It is assumed that the MSA is already aligned.
 */
public class MSASolution extends AbstractSolution<StringBuilder> {

  /**
   * Constructor
   * @param numberOfVariables
   * @param numberOfObjectives
   */
  public MSASolution(int numberOfVariables, int numberOfObjectives) {
    super(numberOfVariables, numberOfObjectives);
  }

  public MSASolution(List<StringBuilder> sequences, int numberOfScores) {
    super(sequences.size(), numberOfScores) ;

    for (int i = 0; i < sequences.size(); i++) {
      variables().set(i, new StringBuilder(sequences.get(i))) ;
    }
  }

  /**
   * Copy constructor
   * @return
   */

  public MSASolution(MSASolution solution) {
    this(solution.variables().size(), solution.objectives().length) ;

    for (int i = 0; i < variables().size(); i++) {
      variables().set(i, new StringBuilder(solution.variables().get(i)));
    }

    for (int i = 0; i < objectives().length; i++) {
      objectives()[i] = solution.objectives()[i];
    }

    for (int i = 0; i < constraints().length; i++) {
      constraints()[i] =  solution.constraints()[i];
    }

    attributes = new HashMap<>(solution.attributes);
  }

  @Override
  public Solution copy() {
    return new MSASolution(this);
  }
}
