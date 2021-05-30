package org.pabuma.ryuq.guidedLocalSearch.Examples;

//import jdk.internal.org.jline.reader.LineReaderBuilder;
import org.jmsa.score.impl.*;
import org.jmsa.substitutionmatrix.SubstitutionMatrix;
import org.jmsa.substitutionmatrix.impl.GenericSubstitutionMatrix;
import org.pabuma.ryuq.component.createinitialsolution.impl.DefaultSolutionCreation;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;
import org.pabuma.ryuq.guidedLocalSearch.GuidedLocalSearch;
import org.pabuma.ryuq.msa.MSAProblem;
import org.pabuma.ryuq.msa.MSASolution;
import org.pabuma.ryuq.msa.mutation.RandomGapInsertion;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.util.observer.impl.PrintObjectivesObserver;

import java.util.List;

public class GuidedLocalSearchMSAExample {

    public static void main(String[] args) throws Exception {
        
        SubstitutionMatrix substitutionMatrix = new GenericSubstitutionMatrix("resources/PAM250Matrix");
        MSAProblem problem = new MSAProblem("resources/BB11001.tfa_clu",
                List.of(new SumOfPairs(substitutionMatrix)));
        MutationOperator<MSASolution> mutation = new RandomGapInsertion(1.0);

        MSASolution initialSolution = new DefaultSolutionCreation<>(problem).create();

        GuidedLocalSearch<MSASolution> glocalSearch = new GuidedLocalSearch<>(
                problem,
                mutation,
                initialSolution,
                new TerminationByEvaluations(100000),100000);

        PrintObjectivesObserver objectivesObserver = new PrintObjectivesObserver(20000);
        glocalSearch.getObservable().register(objectivesObserver);

        glocalSearch.run();
        //
        MSASolution bestFoundSolution = glocalSearch.restart(glocalSearch);
        System.out.println("-----------------------------------------");
        System.out.println("Best solution: " + bestFoundSolution.objectives()[0]);
        //System.out.println("Best solution???: " +glocalSearch.getResult().objectives()[0]);
        System.out.println("Computing tine: " + glocalSearch.getTotalComputingTime());

        problem.writeSequencesToFasta(glocalSearch.getResult().variables(), "output.FASTA");

        glocalSearch.getResult().variables().forEach(System.out::println);

                               //glocalsearch.getresult();
        problem.printMSAScores(bestFoundSolution, List.of(new Entropy(), new Star(substitutionMatrix),
                new SumOfPairs(substitutionMatrix), new PercentageOfTotallyConservedColumns(), new PercentageOfNonGaps()));




    }








}
