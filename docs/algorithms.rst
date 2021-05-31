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
N(s)are forbidden by the tabu list.

Iterated Local Search
---------------------

Variable Neighborhood Search
----------------------------

.. include:: vns.rst

Guided Local Search
-------------------

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







