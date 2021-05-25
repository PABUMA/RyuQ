package org.pabuma.ryuq.variableneighbourhoodsearch.examples;

import org.jmsa.score.impl.*;
import org.jmsa.substitutionmatrix.SubstitutionMatrix;
import org.jmsa.substitutionmatrix.impl.GenericSubstitutionMatrix;
import org.pabuma.ryuq.component.createinitialsolution.impl.DefaultSolutionCreation;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;
import org.pabuma.ryuq.msa.MSAProblem;
import org.pabuma.ryuq.msa.MSASolution;
import org.pabuma.ryuq.msa.mutation.RandomGapInsertion;
import org.pabuma.ryuq.variableneighbourhoodsearch.VariableNeighbourhoodSearch;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.util.observer.impl.PrintObjectivesObserver;

import java.util.List;

public class VariableNeighbourhoodSearchMSA {
    public static void main(String[] args) throws Exception {
        SubstitutionMatrix substitutionMatrix = new GenericSubstitutionMatrix("resources/PAM250Matrix");
        MSAProblem problem = new MSAProblem("resources/BB11001.tfa_clu",
                List.of(new SumOfPairs(substitutionMatrix)));
        MutationOperator<MSASolution> mutation = new RandomGapInsertion(1.0);

        MSASolution initialSolution = new DefaultSolutionCreation<>(problem).create() ;

        VariableNeighbourhoodSearch<MSASolution> vns = new VariableNeighbourhoodSearch<>(
                problem,
                initialSolution,
                new TerminationByEvaluations(1000000),
                mutation,
                5,
                3);

        PrintObjectivesObserver objectivesObserver = new PrintObjectivesObserver(20000);
        vns.getObservable().register(objectivesObserver);

        vns.run();

        System.out.println("Best solution: " + vns.getResult().objectives()[0]);
        System.out.println("Computing time: " + vns.getTotalComputingTime());

        problem.writeSequencesToFasta(vns.getResult().variables(), "output.FASTA");

        vns.getResult().variables().forEach(System.out::println);
        problem.printMSAScores(vns.getResult(), List.of(new Entropy(), new Star(substitutionMatrix),
                new SumOfPairs(substitutionMatrix), new PercentageOfTotallyConservedColumns(), new PercentageOfNonGaps()));
    }
}
