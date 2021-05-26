package org.pabuma.ryuq.tabusearch;

import org.junit.jupiter.api.Test;
import org.pabuma.ryuq.component.createinitialsolution.impl.DefaultSolutionCreation;
import org.uma.jmetal.problem.binaryproblem.BinaryProblem;
import org.uma.jmetal.problem.singleobjective.OneMax;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.solution.binarysolution.BinarySolution;

import static org.junit.jupiter.api.Assertions.*;

class TabuListTest<S extends Solution<?>>{
    @Test
    public void tabuListContains(){
        TabuList tabuList = new TabuList();
        S solution1 =

        tabuList.addList(solution);

        assertTrue(tabuList.inIn(solution)==true);
    }
}