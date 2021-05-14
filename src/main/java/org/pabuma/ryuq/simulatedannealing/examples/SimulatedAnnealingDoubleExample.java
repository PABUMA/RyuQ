package org.pabuma.ryuq.simulatedannealing.examples;

import org.pabuma.ryuq.component.createinitialsolution.impl.DefaultSolutionCreation;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;
import org.pabuma.ryuq.simulatedannealing.SimulatedAnnealing;
import org.pabuma.ryuq.simulatedannealing.cooling.impl.Geometric;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.mutation.impl.PolynomialMutation;
import org.uma.jmetal.problem.doubleproblem.DoubleProblem;
import org.uma.jmetal.problem.singleobjective.Rastrigin;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.util.observer.impl.PrintObjectivesObserver;

public class SimulatedAnnealingDoubleExample {
  public static void main(String[] args) {
    DoubleProblem problem = new Rastrigin(20) ;
    MutationOperator<DoubleSolution> mutation = new PolynomialMutation(1.0/problem.getNumberOfVariables(), 20.0) ;

    SimulatedAnnealing<DoubleSolution> simulatedAnnealing = new SimulatedAnnealing<>(
            problem, mutation, new DefaultSolutionCreation<>(problem), new TerminationByEvaluations(500000),
            1.0, new Geometric(.95)) ;

    PrintObjectivesObserver objectivesObserver = new PrintObjectivesObserver(10000) ;
    simulatedAnnealing.getObservable().register(objectivesObserver);

    simulatedAnnealing.run();

    System.out.println("Best solution: " + simulatedAnnealing.getResult().objectives()[0]) ;
    System.out.println("Computing tine: " + simulatedAnnealing.getTotalComputingTime()) ;
  }
}
