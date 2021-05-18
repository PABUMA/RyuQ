package org.pabuma.ryuq.iteratedlocalsearch.examples;

import org.pabuma.ryuq.component.createinitialsolution.impl.DefaultSolutionCreation;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;
import org.pabuma.ryuq.simulatedannealing.SimulatedAnnealing;
import org.pabuma.ryuq.simulatedannealing.cooling.impl.Geometric;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.mutation.impl.BitFlipMutation;
import org.uma.jmetal.problem.binaryproblem.BinaryProblem;
import org.uma.jmetal.problem.singleobjective.OneMax;
import org.uma.jmetal.solution.binarysolution.BinarySolution;
import org.uma.jmetal.util.observer.impl.PrintObjectivesObserver;

public class IteratedLocalSearchBinaryExample {
    public static void main(String[] args) {
        /*
        int bits = 1024 ;

        BinaryProblem problem = new OneMax(bits) ;
        MutationOperator<BinarySolution> mutation = new BitFlipMutation(1.0/bits) ;

        IteratedLocalSearch<BinarySolution> iteratedLocalSearch = new IteratedLocalSearch<>(
                problem, mutation, new DefaultSolutionCreation<>(problem), new TerminationByEvaluations(20000),
                1.0, new Geometric(.95)) ;

        PrintObjectivesObserver objectivesObserver = new PrintObjectivesObserver(1000) ;
        iteratedLocalSearch.getObservable().register(objectivesObserver);

        iteratedLocalSearch.run();

        System.out.println("Best solution: " + iteratedLocalSearch.getResult().objectives()[0]) ;
        System.out.println("Computing tine: " + iteratedLocalSearch.getTotalComputingTime()) ;

         */
    }
}
