package org.pabuma.ryuq.variableneighbourhoodsearch;

import org.junit.jupiter.api.Test;
import org.pabuma.ryuq.component.createinitialsolution.impl.DefaultSolutionCreation;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.mutation.impl.BitFlipMutation;
import org.uma.jmetal.operator.mutation.impl.PolynomialMutation;
import org.uma.jmetal.problem.binaryproblem.BinaryProblem;
import org.uma.jmetal.problem.doubleproblem.DoubleProblem;
import org.uma.jmetal.problem.singleobjective.OneMax;
import org.uma.jmetal.problem.singleobjective.Rastrigin;
import org.uma.jmetal.solution.binarysolution.BinarySolution;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VariableNeighbourhoodIT {
  @Test
  public void vnsShouldSolveOneMaxProblem() {
    int bits = 1024;
    BinaryProblem problem = new OneMax(bits);
    MutationOperator<BinarySolution> mutation = new BitFlipMutation(1.0 / bits);

    BinarySolution initialSolution = new DefaultSolutionCreation<>(problem).create();

    VariableNeighbourhoodSearch<BinarySolution> vns =
        new VariableNeighbourhoodSearch<>(
            problem, initialSolution, new TerminationByEvaluations(300), mutation, 3, 3, 100);

    vns.run();

    assertTrue(vns.getResult().objectives()[0] * -1 > 1010);
  }

  @Test
  public void vnsShouldSolveRastriginProblem() {
    DoubleProblem problem = new Rastrigin(20);
    MutationOperator<DoubleSolution> mutation =
        new PolynomialMutation(1.0 / problem.getNumberOfVariables(), 20.0);

    DoubleSolution initialSolution = new DefaultSolutionCreation<>(problem).create();

    VariableNeighbourhoodSearch<DoubleSolution> vns =
        new VariableNeighbourhoodSearch<>(
            problem, initialSolution, new TerminationByEvaluations(4000), mutation, 3, 3, 100);

    vns.run();
    System.out.println(vns.getResult().objectives()[0]);
    assertTrue(vns.getResult().objectives()[0] < 0.0001);
  }
}
