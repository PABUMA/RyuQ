package org.pabuma.ryuq.guidedlocalsearch;

import org.junit.jupiter.api.Test;
import org.pabuma.ryuq.component.createinitialsolution.impl.DefaultSolutionCreation;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;
import org.pabuma.ryuq.guidedLocalSearch.GuidedLocalSearch;
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

class GuidedLocalSearchTest {
    @Test
    public void guidedLocalSearchMSAWork() {
        DoubleProblem problem = new Rastrigin(20) ;
        MutationOperator<DoubleSolution> mutation = new PolynomialMutation(1.0/problem.getNumberOfVariables(), 20.0) ;

        DoubleSolution initialSolution = new DefaultSolutionCreation<>(problem).create() ;

        GuidedLocalSearch<DoubleSolution> glocalsearch = new GuidedLocalSearch<>(
                problem, mutation, initialSolution, new TerminationByEvaluations(500000),
                100000) ;

        glocalsearch.run();

        assertTrue(glocalsearch.getResult().objectives()[0] < 0.0001);
    }
}
