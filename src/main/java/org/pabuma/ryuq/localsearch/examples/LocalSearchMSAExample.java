package org.pabuma.ryuq.localsearch.examples;

import org.jmsa.score.impl.*;
import org.jmsa.substitutionmatrix.SubstitutionMatrix;
import org.jmsa.substitutionmatrix.impl.GenericSubstitutionMatrix;
import org.pabuma.ryuq.component.createinitialsolution.impl.DefaultSolutionCreation;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;
import org.pabuma.ryuq.localsearch.LocalSearch;
import org.pabuma.ryuq.msa.MSAProblem;
import org.pabuma.ryuq.msa.MSASolution;
import org.pabuma.ryuq.msa.mutation.RandomGapInsertion;
import org.pabuma.ryuq.simulatedannealing.SimulatedAnnealing;
import org.pabuma.ryuq.simulatedannealing.cooling.impl.Geometric;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.util.observer.impl.PrintObjectivesObserver;

import java.util.List;

public class LocalSearchMSAExample {
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
            new TerminationByEvaluations(1000000));

    PrintObjectivesObserver objectivesObserver = new PrintObjectivesObserver(20000);
    localSearch.getObservable().register(objectivesObserver);

    localSearch.run();

    System.out.println("Best solution: " + localSearch.getResult().objectives()[0]);
    System.out.println("Computing tine: " + localSearch.getTotalComputingTime());

    problem.writeSequencesToFasta(localSearch.getResult().variables(), "output.FASTA");

    localSearch.getResult().variables().forEach(System.out::println);
    problem.printMSAScores(localSearch.getResult(), List.of(new Entropy(), new Star(substitutionMatrix),
            new SumOfPairs(substitutionMatrix), new PercentageOfTotallyConservedColumns(), new PercentageOfNonGaps()));
  }
}
