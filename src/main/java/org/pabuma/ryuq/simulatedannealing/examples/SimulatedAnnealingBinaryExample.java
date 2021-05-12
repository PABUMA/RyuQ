package org.pabuma.ryuq.simulatedannealing.examples;

import org.pabuma.ryuq.simulatedannealing.SimulatedAnnealing;
import org.pabuma.ryuq.simulatedannealing.cooling.impl.Geometric;
import org.uma.jmetal.experimental.componentbasedalgorithm.catalogue.termination.impl.TerminationByEvaluations;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.mutation.impl.BitFlipMutation;
import org.uma.jmetal.problem.binaryproblem.BinaryProblem;
import org.uma.jmetal.problem.singleobjective.OneMax;
import org.uma.jmetal.solution.binarysolution.BinarySolution;
import org.uma.jmetal.util.observer.impl.PrintObjectivesObserver;

public class SimulatedAnnealingBinaryExample {
  public static void main(String[] args) {
    int bits = 1024 ;
    BinaryProblem problem = new OneMax(bits) ;
    MutationOperator<BinarySolution> mutation = new BitFlipMutation(1.0/bits) ;

    SimulatedAnnealing<BinarySolution> simulatedAnnealing = new SimulatedAnnealing<>(
            problem, mutation, new TerminationByEvaluations(20000),
            1.0, new Geometric(.95)) ;

    PrintObjectivesObserver objectivesObserver = new PrintObjectivesObserver(100) ;
    simulatedAnnealing.getObservable().register(objectivesObserver);

    simulatedAnnealing.run();

    System.out.println("Best solution: " + simulatedAnnealing.getResult().objectives()[0]) ;
  }
}
