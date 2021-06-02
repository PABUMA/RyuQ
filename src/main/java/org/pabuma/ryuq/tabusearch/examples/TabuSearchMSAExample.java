package org.pabuma.ryuq.tabusearch.examples;

import org.jmsa.score.impl.*;
import org.jmsa.substitutionmatrix.SubstitutionMatrix;
import org.jmsa.substitutionmatrix.impl.GenericSubstitutionMatrix;
import org.pabuma.ryuq.component.createinitialsolution.impl.DefaultSolutionCreation;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;
import org.pabuma.ryuq.msa.MSAProblem;
import org.pabuma.ryuq.msa.MSASolution;
import org.pabuma.ryuq.msa.mutation.RandomGapInsertion;
import org.pabuma.ryuq.tabusearch.TabuSearch;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.util.observer.impl.PrintObjectivesObserver;

import java.util.List;

public class TabuSearchMSAExample {
    public static void main(String[] args) throws Exception {

        SubstitutionMatrix substitutionMatrix = new GenericSubstitutionMatrix("resources/PAM250Matrix");
        MSAProblem problem = new MSAProblem("resources/BB11001.tfa_clu",
                List.of(new SumOfPairs(substitutionMatrix)));
        MutationOperator<MSASolution> mutation = new RandomGapInsertion(1.0);


        TabuSearch<MSASolution> tabusearch = new TabuSearch<>(
                problem,
                mutation,
                new DefaultSolutionCreation<>(problem),
                new TerminationByEvaluations(1000000));

        PrintObjectivesObserver objectivesObserver = new PrintObjectivesObserver(20000);
        tabusearch.getObservable().register(objectivesObserver);

        tabusearch.run();

        System.out.println("Best solution: " + tabusearch.getResult().objectives()[0]);
        System.out.println("Computing tine: " + tabusearch.getTotalComputingTime());

        problem.writeSequencesToFasta(tabusearch.getResult().variables(), "output.FASTA");

        tabusearch.getResult().variables().forEach(System.out::println);
        problem.printMSAScores(tabusearch.getResult(), List.of(new Entropy(), new Star(substitutionMatrix),
                new SumOfPairs(substitutionMatrix), new PercentageOfTotallyConservedColumns(), new PercentageOfNonGaps()));
    }
}