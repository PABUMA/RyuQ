Algorithms
==========

Local Search
------------

Tabu Search
-----------

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







