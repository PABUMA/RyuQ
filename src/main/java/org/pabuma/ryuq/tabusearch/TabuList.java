package org.pabuma.ryuq.tabusearch;

import org.uma.jmetal.solution.Solution;

import java.util.ArrayList;
import java.util.List;

public class TabuList <S extends Solution<?>> {
    List<S> tabuList;

    public TabuList(){
        tabuList=new ArrayList<>();
    }

    public boolean inIn(S solution){
        return tabuList.contains(solution);
    }

    public void addList(S solution){
        tabuList.add(solution);
    }
}
