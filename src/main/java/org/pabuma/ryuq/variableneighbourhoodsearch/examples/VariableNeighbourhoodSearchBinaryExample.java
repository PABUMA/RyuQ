package org.pabuma.ryuq.variableneighbourhoodsearch.examples;

import org.pabuma.ryuq.component.createinitialsolution.impl.DefaultSolutionCreation;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;
import org.pabuma.ryuq.variableneighbourhoodsearch.VariableNeighbourhoodSearch;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.mutation.impl.BitFlipMutation;
import org.uma.jmetal.problem.binaryproblem.BinaryProblem;
import org.uma.jmetal.problem.singleobjective.OneMax;
import org.uma.jmetal.solution.binarysolution.BinarySolution;
import org.uma.jmetal.util.observer.impl.PrintObjectivesObserver;

public class VariableNeighbourhoodSearchBinaryExample {
  public static void main(String[] args) {
    int bits = 1024;
    BinaryProblem problem = new OneMax(bits);
    MutationOperator<BinarySolution> mutation = new BitFlipMutation(1.0 / bits);

    BinarySolution initialSolution = new DefaultSolutionCreation<>(problem).create() ;

    VariableNeighbourhoodSearch<BinarySolution> vns =
        new VariableNeighbourhoodSearch<>(
            problem,
            initialSolution,
            new TerminationByEvaluations(20000),
            mutation,
            10);

    PrintObjectivesObserver objectivesObserver = new PrintObjectivesObserver(1000);
    vns.getObservable().register(objectivesObserver);

    vns.run();

    System.out.println("Best solution: " + vns.getResult().objectives()[0]);
    System.out.println("Computing time: " + vns.getTotalComputingTime());
  }
}
