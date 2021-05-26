package org.pabuma.ryuq.localsearch.examples;

import org.pabuma.ryuq.component.createinitialsolution.impl.DefaultSolutionCreation;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;
import org.pabuma.ryuq.localsearch.LocalSearch;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.mutation.impl.BitFlipMutation;
import org.uma.jmetal.problem.binaryproblem.BinaryProblem;
import org.uma.jmetal.problem.singleobjective.OneMax;
import org.uma.jmetal.solution.binarysolution.BinarySolution;
import org.uma.jmetal.util.observer.impl.PrintObjectivesObserver;

public class LocalSearchBinaryExample {
    public static void main(String[] args) {
        int bits = 1024 ;
        BinaryProblem problem = new OneMax(bits) ;
        MutationOperator<BinarySolution> mutation = new BitFlipMutation(1.0/bits) ;

        BinarySolution initialSolution = new DefaultSolutionCreation<>(problem).create() ;

        LocalSearch<BinarySolution> localSearch = new LocalSearch<>(
                problem, mutation, initialSolution, new TerminationByEvaluations(20000)) ;

        PrintObjectivesObserver objectivesObserver = new PrintObjectivesObserver(1000) ;
        localSearch.getObservable().register(objectivesObserver);

        localSearch.run();

        System.out.println("Best solution: " + localSearch.getResult().objectives()[0]) ;
        System.out.println("Computing tine: " + localSearch.getTotalComputingTime()) ;
    }
}
