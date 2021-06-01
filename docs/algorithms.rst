Algorithms
==========

Local Search
------------

Tabu Search
-----------
Tabu Search is among the most cited and used metaheuristics for CO problems. It can be considered as a dynamic
neighborhood search technique.

The pseudo-code of a basic tabu search is the following:

.. code-block:: python

        def tabu_search():
            s = Generate an initial solution
            TabuList = null
            while (!stoppingCondition):
                s = ChooseBestOf(N(s)\TabuList)
                Update TabuList

The simple TS algorithm applies a best improvement local search as basic ingredient and
uses a short term memory to escape from local minima and to avoid cycles. The short term memory is implemented as a tabu
list that keeps track of the most recently visited solutions and forbids moves toward them. The neighborhood of the
current solution is thus restricted to the solutions that do not belong to the tabu list. The algorithm stops when a
termination condition is met. It might also terminate if the allowed set is empty, that is, if all the solutions in
N(s) are forbidden by the tabu list.

.. figure:: /docs/figures/imagets.png
    :width: 500
    :align: center

Iterated Local Search
---------------------

.. include:: ils.rst

Variable Neighborhood Search
----------------------------

.. include:: vns.rst

Guided Local Search
-------------------
The local guided search is a metaheuristic search method. The method is an alternative to the local search, but more effective. GLS has the idea of increasing the objective function with penalties, these are used to escape the local plateaus and minimum and finally establish a local optimum.

The GLS pseudocode is shown below:

.. code-block:: python

    def guided_local_search():
        s= GenerateInitialSolution()
        while(!TerminatedCondition):
            s= LocalSearch(s,f')
              for(max(Util(s,i))):
                           Pi++;
            Update(f',P)


The algorithm starts from an initial solution and applies a local search method until obtain a local minimum. Then, the matrix of penalties is updated while increasing some penalties and in turn, the local search is started again.
The Util function tries to penalize an attribute i given a local optimum s*, and finally the most useful attribute is the penalized one. The Util parameters are ci which is the cost of the attribute, pi is the current criminalization of the attribute i, and finally, I(s) is an indicator that indicates if the characteristic i is present in the solution s:

.. figure:: /docs/figures/UtilFunction.png
    :width: 500
    :align: center

The local guided search is complicated to implement, so an adaptation of this method was made. We have made a loop of n iterations in which we do a local search.
Start with an initial solution and look to your neighborhood for a better solution. If you find it, replace your current solution with the new one and continue with the process, until you can’t improve the current solution, and so on.
We do this because we use the Local Search algorithm with Restart which is something different from the original Local Search algorithm.
With the original algorithm, in each iteration of the loop, we start from a different initial solution; having as many initial solutions as iterations are done in the loop.
With Restart what we do is to start from the same initial solution in all iterations, thus achieving a more reliable comparison of results since we start from the same initial solution.

.. code-block:: python

    def guided_local_search():
        currentSolution= GenerateInitialSolution()
        while(iterations):
            s=mutatedSolution
             if(mutatedSolution<s):
            currentSolution=s;
            iterations=0;
         else:
            iterations++
        Update(CurrentSolution)

Simulated Annealing
-------------------
Simulated annealing is one of the oldest metaheuristics. This algorithm behaves as a local search but, with the idea
of scapoing from local minima, it is possible to accept worst solutions than the current one. The acceptance depends
of a probability that decreases with time. The name of the technique comes a process known as annealing in metallurgy
(a material is heated and the temperature is slowly lowered to decrease deffects, leading to a minimization of the
system energy) .

The pseudo-code of a generic simulated annealing is the following:

.. code-block:: python

    def simulated_annealing():
        s = Generate an initial solution
        T = T0
        while (!stoppingCondition):
        s' = Pick a random solution from the neighborhood of s
            if s' < s:
                s = s'
            else
                Accept s' as current solution with probability p(T, s, s')
            Update T







