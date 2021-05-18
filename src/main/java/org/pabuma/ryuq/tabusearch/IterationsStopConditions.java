package org.pabuma.ryuq.tabusearch;

public class IterationsStopConditions {
    public class IterationsStopCondition implements StopCondition {

        private final Integer maxIterations;

        public IterationsStopCondition(Integer maxIterations) {
            this.maxIterations = maxIterations;
        }

        @Override
        public Boolean mustStop(Integer currentIteration, Solution bestSolutionFound) {
            return currentIteration >= maxIterations;
        }

    }
}
