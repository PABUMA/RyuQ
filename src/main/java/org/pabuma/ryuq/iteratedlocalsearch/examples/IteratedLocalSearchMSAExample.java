package org.pabuma.ryuq.iteratedlocalsearch.examples;

import org.jmsa.score.impl.*;
import org.jmsa.substitutionmatrix.SubstitutionMatrix;
import org.jmsa.substitutionmatrix.impl.GenericSubstitutionMatrix;
import org.pabuma.ryuq.component.createinitialsolution.impl.DefaultSolutionCreation;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;
import org.pabuma.ryuq.iteratedlocalsearch.IteratedLocalSearch;
import org.pabuma.ryuq.msa.MSAProblem;
import org.pabuma.ryuq.msa.MSASolution;
import org.pabuma.ryuq.msa.mutation.RandomGapInsertion;
import org.pabuma.ryuq.simulatedannealing.SimulatedAnnealing;
import org.pabuma.ryuq.simulatedannealing.cooling.impl.Geometric;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.util.observer.impl.PrintObjectivesObserver;

import java.util.List;

public class IteratedLocalSearchMSAExample {
    public static void main(String[] args) throws Exception {
        SubstitutionMatrix substitutionMatrix = new GenericSubstitutionMatrix("resources/PAM250Matrix");
        MSAProblem problem = new MSAProblem("resources/BB11001.tfa_clu",
                List.of(new SumOfPairs(substitutionMatrix)));
        MutationOperator<MSASolution> mutation = new RandomGapInsertion(1.0);

        IteratedLocalSearch<MSASolution> iteratedLocalSearch = new IteratedLocalSearch<>(
                problem,
                mutation,
                new DefaultSolutionCreation<>(problem),
                new TerminationByEvaluations(100000));

        PrintObjectivesObserver objectivesObserver = new PrintObjectivesObserver(2000);
        iteratedLocalSearch.getObservable().register(objectivesObserver);

        iteratedLocalSearch.run();

        System.out.println("Best solution: " + iteratedLocalSearch.getResult().objectives()[0]);
        System.out.println("Computing tine: " + iteratedLocalSearch.getTotalComputingTime());

        problem.writeSequencesToFasta(iteratedLocalSearch.getResult().variables(), "output.FASTA");

        iteratedLocalSearch.getResult().variables().forEach(System.out::println);
        problem.printMSAScores(iteratedLocalSearch.getResult(), List.of(new Entropy(), new Star(substitutionMatrix),
                new SumOfPairs(substitutionMatrix), new PercentageOfTotallyConservedColumns(), new PercentageOfNonGaps()));
    }
}
