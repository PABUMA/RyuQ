package org.pabuma.ryuq.tabusearch;
import java.util.List;

public interface Solution {


    Double getValue();

    List<Solution> getNeighbors();

}