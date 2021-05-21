package org.pabuma.ryuq.simulatedannealing;

import org.junit.jupiter.api.Test;
import org.pabuma.ryuq.component.createinitialsolution.impl.DefaultSolutionCreation;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;
import org.pabuma.ryuq.localsearch.LocalSearch;
import org.pabuma.ryuq.simulatedannealing.cooling.impl.Geometric;
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

class LocalSearchIT {
  @Test
  public void shouldSimulatedAnnealingWorkProperlyWhenSolvingTheOneMaxProblem() {
    int bits = 1024;
    BinaryProblem problem = new OneMax(bits);
    MutationOperator<BinarySolution> mutation = new BitFlipMutation(1.0 / bits);

    BinarySolution initialSolution = new DefaultSolutionCreation<>(problem).create() ;

    LocalSearch<BinarySolution> localSearch = new LocalSearch<>(
            problem, mutation, initialSolution, new TerminationByEvaluations(20000));

    localSearch.run();

    assertTrue(localSearch.getResult().objectives()[0] * -1 > 1000);
  }

  @Test
  public void shouldSimulatedAnnealingWorkProperlyWhenSolvingTheRastriginProblem() {
    DoubleProblem problem = new Rastrigin(20) ;
    MutationOperator<DoubleSolution> mutation = new PolynomialMutation(1.0/problem.getNumberOfVariables(), 20.0) ;

    DoubleSolution initialSolution = new DefaultSolutionCreation<>(problem).create() ;

    LocalSearch<DoubleSolution> localSearch = new LocalSearch<>(
            problem, mutation, initialSolution, new TerminationByEvaluations(500000)) ;

    localSearch.run();

    assertTrue(localSearch.getResult().objectives()[0] < 0.0001);
  }
}