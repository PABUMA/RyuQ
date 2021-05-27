package org.pabuma.ryuq.guidedLocalSearch.Examples;

import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.mutation.impl.BitFlipMutation;
import org.uma.jmetal.problem.binaryproblem.BinaryProblem;
import org.uma.jmetal.problem.singleobjective.OneMax;
import org.uma.jmetal.solution.binarysolution.BinarySolution;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuidedLocalSearchBinaryExample {
    public static void main(String[] args) {
        int bits = 1024;
        BinaryProblem problem = new OneMax(bits);
        MutationOperator<BinarySolution> mutation = new BitFlipMutation(1.0 / bits);

        BinarySolution currentSolution = problem.createSolution();
        problem.evaluate(currentSolution);


        int evaluationCounter=1;

        while (evaluationCounter<10000) {
            BinarySolution mutatedSolution = mutation.execute((BinarySolution) currentSolution.copy()) ;
            problem.evaluate(mutatedSolution) ;
            evaluationCounter++;

            if (currentSolution.objectives()[0] < mutatedSolution.objectives()[0]) {
                    currentSolution=mutatedSolution;
            }


        }
        //GuidedLocalSearch<BinarySolution> guidedLocalSearch = new GuidedLocalSearch<BinarySolution>(problem, mutation, new DefaultSolutionCreation<>(problem), new TerminationByEvaluations(20000));

        //guidedLocalSearch.run();

        //assertTrue(guidedLocalSearch.getResult().objectives()[0] * -1 > 1000);
    }

}
