package org.pabuma.ryuq.guidedLocalSearch.Examples;

import org.junit.jupiter.api.Test;
import org.pabuma.ryuq.component.createinitialsolution.impl.DefaultSolutionCreation;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;
import org.pabuma.ryuq.guidedLocalSearch.guidedLocalSearch;
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

public class guidedLocalSearchBinaryExample {
    @Test
    public void shouldGuidedLocalSearchWorkProperlyWhenSolvingTheOneMaxProblem() {
        int bits = 1024;
        BinaryProblem problem = new OneMax(bits);
        MutationOperator<BinarySolution> mutation = new BitFlipMutation(1.0 / bits);

        BinarySolution currentSolution = problem.createSolution() ;
        problem.evaluate(currentSolution) ;

        while (true) {
            BinarySolution mutatedSolution = mutation.execute((BinarySolution) currentSolution.copy()) ;
            problem.evaluate(mutatedSolution) ;

            if (currentSolution.objectives()[0] < mutatedSolution.objectives()[0]) {

            }

        }

        guidedLocalSearch<BinarySolution> guidedLocalSearch = new guidedLocalSearch<>(problem, mutation, new DefaultSolutionCreation<>(problem), new TerminationByEvaluations(20000));

        guidedLocalSearch.run();

        assertTrue(guidedLocalSearch.getResult().objectives()[0] * -1 > 1000);
    }

}
