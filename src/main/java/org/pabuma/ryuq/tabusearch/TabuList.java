package org.pabuma.ryuq.tabusearch;

import org.uma.jmetal.solution.Solution;

import java.util.ArrayList;
import java.util.List;

public class TabuList <S extends Solution<?>> {
    List<String> tabuList;

    public TabuList(){
        tabuList=new ArrayList<>();
    }

    public boolean inIn(Solution<?> solution){
        String sequenceString = "" ;
        for (int i = 0 ; i < solution.variables().size(); i++) {
            sequenceString += solution.variables().get(i).toString() ;
        }

        boolean isIn = tabuList.contains(sequenceString);
        return isIn;
    }

    public void addList(Solution<?> solution){
        String sequenceString = "" ;
        for (int i = 0 ; i < solution.variables().size(); i++) {
            sequenceString += solution.variables().get(i).toString() ;
        }

        tabuList.add(sequenceString);
    }
}