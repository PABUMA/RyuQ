package org.pabuma.ryuq.simulatedannealing.examples;

import org.jmsa.score.impl.Entropy;
import org.jmsa.score.impl.PercentageOfTotallyConservedColumns;
import org.jmsa.score.impl.SumOfPairs;
import org.jmsa.substitutionmatrix.impl.GenericSubstitutionMatrix;
import org.pabuma.ryuq.component.createinitialsolution.impl.DefaultSolutionCreation;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;
import org.pabuma.ryuq.msa.MSAProblem;
import org.pabuma.ryuq.msa.MSASolution;
import org.pabuma.ryuq.msa.mutation.RandomGapInsertion;
import org.pabuma.ryuq.simulatedannealing.SimulatedAnnealing;
import org.pabuma.ryuq.simulatedannealing.cooling.impl.Geometric;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.mutation.impl.PolynomialMutation;
import org.uma.jmetal.problem.doubleproblem.DoubleProblem;
import org.uma.jmetal.problem.singleobjective.Rastrigin;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.util.observer.impl.PrintObjectivesObserver;

import java.io.IOException;
import java.util.List;

public class SimulatedAnnealingMSAExample {
  public static void main(String[] args) throws Exception {
    MSAProblem problem = new MSAProblem("resources/BB11001.fasta",
            List.of(new SumOfPairs(new GenericSubstitutionMatrix("resources/PAM250Matrix")))) ;
    MutationOperator<MSASolution> mutation = new RandomGapInsertion(1.0) ;

    SimulatedAnnealing<MSASolution> simulatedAnnealing = new SimulatedAnnealing<>(
            problem, mutation, new DefaultSolutionCreation<>(problem), new TerminationByEvaluations(100000),
            1.0, new Geometric(.95)) ;

    PrintObjectivesObserver objectivesObserver = new PrintObjectivesObserver(20000) ;
    simulatedAnnealing.getObservable().register(objectivesObserver);

    simulatedAnnealing.run();

    System.out.println("Best solution: " + simulatedAnnealing.getResult().objectives()[0]) ;
    System.out.println("Computing tine: " + simulatedAnnealing.getTotalComputingTime()) ;

    problem.writeSequencesToFasta(simulatedAnnealing.getResult().variables(), "output.FASTA");
  }
}
