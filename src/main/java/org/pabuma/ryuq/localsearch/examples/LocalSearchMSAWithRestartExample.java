package org.pabuma.ryuq.localsearch.examples;

import org.jmsa.score.impl.*;
import org.jmsa.substitutionmatrix.SubstitutionMatrix;
import org.jmsa.substitutionmatrix.impl.GenericSubstitutionMatrix;
import org.pabuma.ryuq.component.createinitialsolution.impl.DefaultSolutionCreation;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByLimitOfIterationsWithoutImprovement;
import org.pabuma.ryuq.localsearch.LocalSearch;
import org.pabuma.ryuq.msa.MSAProblem;
import org.pabuma.ryuq.msa.MSASolution;
import org.pabuma.ryuq.msa.mutation.RandomGapInsertion;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.util.observer.impl.PrintObjectivesObserver;

import java.util.List;

public class LocalSearchMSAWithRestartExample {
  public static void main(String[] args) throws Exception {
    SubstitutionMatrix substitutionMatrix = new GenericSubstitutionMatrix("resources/PAM250Matrix");
    MSAProblem problem = new MSAProblem("resources/BB11001.tfa_clu",
            List.of(new SumOfPairs(substitutionMatrix)));
    MutationOperator<MSASolution> mutation = new RandomGapInsertion(1.0);

    MSASolution initialSolution = new DefaultSolutionCreation<>(problem).create() ;

    LocalSearch<MSASolution> localSearch = new LocalSearch<>(
            problem,
            mutation,
            initialSolution,
            new TerminationByLimitOfIterationsWithoutImprovement(50000));

    localSearch.run();
    MSASolution bestFoundSolution = localSearch.getResult() ;

    int numberOfRestarts = 10 ;
    int restartCounters = 0 ;
    while (restartCounters < numberOfRestarts) {
      System.out.println("Restart: " + restartCounters) ;
      System.out.println("Current found fitness: " + localSearch.getResult().objectives()[0]) ;
      System.out.println("Best found fitness: " + bestFoundSolution.objectives()[0]) ;

      MSASolution newInitialSolution = problem.createSolution();
      problem.evaluate(newInitialSolution) ;
      localSearch.setInitialSolution(newInitialSolution);
      localSearch.run();

      if (localSearch.getResult().objectives()[0] < bestFoundSolution.objectives()[0]) {
        bestFoundSolution = localSearch.getResult() ;
      }
      restartCounters ++ ;
    }

    System.out.println("Best solution: " + bestFoundSolution.objectives()[0]);

    problem.writeSequencesToFasta(bestFoundSolution.variables(), "output.FASTA");

    bestFoundSolution.variables().forEach(System.out::println);
    problem.printMSAScores(bestFoundSolution, List.of(new Entropy(), new Star(substitutionMatrix),
            new SumOfPairs(substitutionMatrix), new PercentageOfTotallyConservedColumns(), new PercentageOfNonGaps()));
  }
}
