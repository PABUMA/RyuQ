package org.pabuma.ryuq.localsearch.examples;

import org.pabuma.ryuq.component.createinitialsolution.impl.DefaultSolutionCreation;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;
import org.pabuma.ryuq.localsearch.LocalSearch;
<<<<<<< HEAD
import org.pabuma.ryuq.simulatedannealing.SimulatedAnnealing;
import org.pabuma.ryuq.simulatedannealing.cooling.impl.Geometric;
=======
>>>>>>> simulatedannealing
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.mutation.impl.PolynomialMutation;
import org.uma.jmetal.problem.doubleproblem.DoubleProblem;
import org.uma.jmetal.problem.singleobjective.Rastrigin;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.util.observer.impl.PrintObjectivesObserver;

public class LocalSearchDoubleExample {
  public static void main(String[] args) {
    DoubleProblem problem = new Rastrigin(20) ;
    MutationOperator<DoubleSolution> mutation = new PolynomialMutation(1.0/problem.getNumberOfVariables(), 20.0) ;

<<<<<<< HEAD
    LocalSearch<DoubleSolution> localSearch = new LocalSearch<>(
            problem, mutation, new DefaultSolutionCreation<>(problem), new TerminationByEvaluations(500000)) ;
=======
    DoubleSolution initialSolution = new DefaultSolutionCreation<>(problem).create() ;

    LocalSearch<DoubleSolution> localSearch = new LocalSearch<>(
            problem, mutation, initialSolution, new TerminationByEvaluations(500000)) ;
>>>>>>> simulatedannealing

    PrintObjectivesObserver objectivesObserver = new PrintObjectivesObserver(10000) ;
    localSearch.getObservable().register(objectivesObserver);

    localSearch.run();

    System.out.println("Best solution: " + localSearch.getResult().objectives()[0]) ;
    System.out.println("Computing tine: " + localSearch.getTotalComputingTime()) ;
  }
}
