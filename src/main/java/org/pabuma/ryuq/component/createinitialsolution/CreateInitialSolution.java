package org.pabuma.ryuq.component.createinitialsolution;

import org.uma.jmetal.solution.Solution;

/**
 * Interface representing entities that create a new solution (e.g., randomly)
 *
 * @param <S>
 */
public interface CreateInitialSolution <S extends Solution<?>>{
  S create() ;
}
