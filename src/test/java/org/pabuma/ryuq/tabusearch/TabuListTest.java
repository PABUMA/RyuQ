package org.pabuma.ryuq.tabusearch;

import org.jmsa.score.impl.SumOfPairs;
import org.jmsa.substitutionmatrix.SubstitutionMatrix;
import org.jmsa.substitutionmatrix.impl.GenericSubstitutionMatrix;
import org.junit.jupiter.api.Test;
import org.pabuma.ryuq.component.createinitialsolution.impl.DefaultSolutionCreation;
import org.pabuma.ryuq.component.terminationcondition.impl.TerminationByEvaluations;
import org.pabuma.ryuq.msa.MSAProblem;
import org.pabuma.ryuq.msa.MSASolution;
import org.pabuma.ryuq.msa.mutation.RandomGapInsertion;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.problem.binaryproblem.BinaryProblem;
import org.uma.jmetal.problem.singleobjective.OneMax;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.solution.binarysolution.BinarySolution;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TabuListTest<S extends Solution<?>>{

    @Test
    public void tabuListContains(){
        BinaryProblem problem = new OneMax(512);
        TabuList tabuList = new TabuList(100);
        BinarySolution solution = problem.createSolution();
        BinarySolution solution1 = (BinarySolution) solution.copy();
        problem.evaluate(solution);
        tabuList.addList(solution);

        assertTrue(tabuList.inIn(solution1));
    }

    @Test
    public void tabuListNotContains(){
        BinaryProblem problem = new OneMax(512);
        TabuList tabuList = new TabuList(100);
        BinarySolution solution = problem.createSolution();
        BinarySolution solution1 = problem.createSolution();
        tabuList.addList(solution);

        assertFalse(tabuList.inIn(solution1));
    }

    @Test
    public void tabuListWithMSAProblem() throws IOException {
        TabuList tabuList = new TabuList(100);
        SubstitutionMatrix substitutionMatrix = new GenericSubstitutionMatrix("resources/PAM250Matrix");
        MSAProblem problem = new MSAProblem("resources/BB11001.tfa_muscle",
                List.of(new SumOfPairs(substitutionMatrix)));
        MSASolution solution = problem.createSolution();
        MSASolution solution1 = (MSASolution) solution.copy();
        problem.evaluate(solution);
        tabuList.addList(solution);

        assertTrue(tabuList.inIn((Solution<?>) solution1));
    }
}