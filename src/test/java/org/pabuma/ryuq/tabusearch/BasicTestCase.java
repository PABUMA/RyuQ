package org.pabuma.ryuq.tabusearch;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;


public class BasicTestCase {

    private List<Solutions> solutions;

    private static class SomeSolution implements Solutions { //creamos una implementación de Solutions con números random

        Double value;
        List<Solutions> neighbors;

        @Override
        public Double getValue() {
            return value;
        }

        @Override
        public List<Solutions> getNeighbors() {
            return neighbors;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((value == null) ? 0 : value.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            SomeSolution other = (SomeSolution) obj;
            if (value == null) {
                if (other.value != null)
                    return false;
            } else if (!value.equals(other.value))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return value.toString();
        }

    }

    @Before
    public void buildInstance() {
        Integer instanceSize = 1000;

        solutions = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < instanceSize; i++) { // se añaden a la lista de soluciones 1000 soluciones
            Double currentValue = random.nextDouble() * instanceSize;

            SomeSolution solution = new SomeSolution();
            solution.neighbors = new LinkedList<>();
            solution.value = currentValue;

            solutions.add(solution);
        }

        Collections.shuffle(solutions);

        for (int i = 0; i < instanceSize; i++) { // para cada solución añadimos de 3 a 10 neighbors
            Solutions solution = solutions.get(i);

            Integer neighborsCount = random.nextInt(7) + 3;

            for (int j = 0; j < neighborsCount; j++) { // se añaden los neighbors a la lista de neighbors
                Solutions randomNeighbor = solutions.get(random.nextInt(instanceSize));
                solution.getNeighbors().add(randomNeighbor);
            }
        }

        Collections.sort(solutions, new Comparator<Solutions>() {
            @Override
            public int compare(Solutions a, Solutions b) {
                return a.getValue().compareTo(b.getValue());
            }
        });
    }

    @Test
    public void runAlgorithm() {
        Solutions initialSolution = solutions.get(new Random().nextInt(solutions.size()));

        for (int s = 5; s <= 10; s++) { //the size of the tabu list
            for (double i = 0.5; i <= 2; i += 0.5) { //the amount of iterations (50%, 100%, 150% and 200%)
                Integer maxIterations = new Double(solutions.size() * i).intValue();

                System.out.println(String.format("Running TS with tabu list size [%s] and [%s] iterations. Instance size [%s]", s, maxIterations, solutions.size()));

                TabuSearch ts = setupTS(s, maxIterations);
                Solutions returnValue = ts.run(initialSolution);

                Integer returnedValueIndex = solutions.indexOf(returnValue);
                System.out.println(String.format("The algorithm returned a result with [%s] units of distance of the best solution", returnedValueIndex));
            }
        }
    }

    public TabuSearch setupTS(Integer tabuListSize, Integer iterations) {
        return new TabuSearch(new StaticTabuList(tabuListSize), new IterationsStopCondition(iterations), new BasicNeighborSolutionLocator());
    }
}