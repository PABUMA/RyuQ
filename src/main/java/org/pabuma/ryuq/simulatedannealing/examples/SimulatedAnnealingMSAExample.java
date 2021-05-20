package org.pabuma.ryuq.simulatedannealing.examples;

import org.jmsa.score.impl.*;
import org.jmsa.substitutionmatrix.SubstitutionMatrix;
import org.jmsa.substitutionmatrix.impl.GenericSubstitutionMatrix;
import org.pabuma.ryuq.component.createinitialsolution.impl.DefaultSolutionCreation;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;
import org.pabuma.ryuq.msa.MSAProblem;
import org.pabuma.ryuq.msa.MSASolution;
import org.pabuma.ryuq.msa.mutation.RandomGapInsertion;
import org.pabuma.ryuq.simulatedannealing.SimulatedAnnealing;
import org.pabuma.ryuq.simulatedannealing.cooling.impl.Geometric;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.util.observer.impl.PrintObjectivesObserver;

import java.util.List;

public class SimulatedAnnealingMSAExample {
  public static void main(String[] args) throws Exception {
    SubstitutionMatrix substitutionMatrix = new GenericSubstitutionMatrix("resources/PAM250Matrix");
    MSAProblem problem = new MSAProblem("resources/BB11001.fasta",
            List.of(new SumOfPairs(substitutionMatrix)));
    MutationOperator<MSASolution> mutation = new RandomGapInsertion(1.0);

    SimulatedAnnealing<MSASolution> simulatedAnnealing = new SimulatedAnnealing<>(
            problem, mutation, new DefaultSolutionCreation<>(problem), new TerminationByEvaluations(1000000),
            1.0, new Geometric(.95));

    PrintObjectivesObserver objectivesObserver = new PrintObjectivesObserver(20000);
    simulatedAnnealing.getObservable().register(objectivesObserver);

    simulatedAnnealing.run();

    System.out.println("Best solution: " + simulatedAnnealing.getResult().objectives()[0]);
    System.out.println("Computing tine: " + simulatedAnnealing.getTotalComputingTime());

    problem.writeSequencesToFasta(simulatedAnnealing.getResult().variables(), "output.FASTA");

    simulatedAnnealing.getResult().variables().forEach(i -> System.out.println(i));
    problem.printMSAScores(simulatedAnnealing.getResult(), List.of(new Entropy(), new Star(substitutionMatrix),
            new SumOfPairs(substitutionMatrix), new PercentageOfTotallyConservedColumns(), new PercentageOfNonGaps()));
  }
}
