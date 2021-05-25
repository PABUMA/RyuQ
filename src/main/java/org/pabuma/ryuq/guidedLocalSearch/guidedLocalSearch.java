package org.pabuma.ryuq.guidedLocalSearch;

import org.pabuma.ryuq.msa.MSASolution;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.solution.Solution;

import org.pabuma.ryuq.TrajectoryAlgorithm;
import org.pabuma.ryuq.component.createinitialsolution.CreateInitialSolution;
import org.pabuma.ryuq.component.terminationcondition.TerminationCondition;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

public class guidedLocalSearch <S extends Solution<?>> extends TrajectoryAlgorithm<S> {

    private MutationOperator<S> mutationOperator;

  public guidedLocalSearch(Problem<S> problem,
                MutationOperator<S> mutation,
                           S initialSolution,
                TerminationCondition terminationCriterion) {

            super(problem, initialSolution, terminationCriterion) ;
            this.mutationOperator = mutation;
        }


    public void utils(MSASolution initialSolution){
        int i;
        boolean I;
        Integer cont=0;
        for (i = 0; i < initialSolution.variables().size(); i++) {
            if(     initialSolution.variables().get(0).charAt(i)==initialSolution.variables().get(1).charAt(i)||
                    initialSolution.variables().get(0).charAt(i)==initialSolution.variables().get(2).charAt(i)||
                    initialSolution.variables().get(0).charAt(i)==initialSolution.variables().get(3).charAt(i)
            ) {
                cont++;
            }
        }

        if(cont>4){
            I=true;
        }
        else{
            I=false;
        }

    }



    @Override
    public S upgrade(S currentSolution) {
        return null;
    }
    @Override
    public String getName() {
        return "GLS";
    }
    @Override
    public String getDescription() {
        return "Guided Local Search ";
    }


}
