package org.pabuma.ryuq.tabusearch;

import java.util.Iterator;

import org.apache.commons.collections4.queue.CircularFifoQueue;

public final class StaticTabuList implements TabuList {

    private CircularFifoQueue<Solutions> tabuList;

    public StaticTabuList(Integer size) {
        this.tabuList = new CircularFifoQueue<Solutions>(size);
    }

    @Override
    public void add(Solutions solution) {
        tabuList.add(solution);
    }

    @Override
    public Boolean contains(Solutions solution) {
        return tabuList.contains(solution);
    }

    @Override
    public Iterator<Solutions> iterator() {
        return tabuList.iterator();
    }

    @Override
    public void updateSize(Integer currentIteration, Solutions bestSolutionFound) {
        //Do nothing, this implementation has a fixed size
    }

}