package org.pabuma.ryuq.component.algorithmstep;

import org.uma.jmetal.solution.Solution;

public interface AlgorithmStep<S extends Solution<?>> {
  S upgrade(S solution) ;
}
