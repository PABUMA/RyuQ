package org.pabuma.ryuq.tabusearch;

import org.uma.jmetal.solution.Solution;

import java.util.*;

public class TabuList <S extends Solution<?>> {
    Queue tabuList;
    int maxSize;

    public TabuList(int size){
        tabuList=new LinkedList<String>();
        maxSize = size;
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
        if (tabuList.size() == maxSize) {
            tabuList.poll();
        }
        for (int i = 0 ; i < solution.variables().size(); i++) {
            sequenceString += solution.variables().get(i).toString() ;
        }
        tabuList.add(sequenceString);
    }
}