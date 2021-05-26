Variable neighborhood search (VNS) is a recent metaheuristic for solving combinatorial and global optimization problems whose basic idea is systematic change of neighborhood
within a local search. In this survey paper we present basic rules of VNS and some of its extensions. Moreover, applications are briefly summarized. They comprise heuristic solution of a
variety of optimization problems, ways to accelerate exact algorithms and to analyze heuristic
solution processes, as well as computer-assisted discovery of conjectures in graph theory.

*Source*: `Variable neighbourhood search <https://sci2s.ugr.es/sites/default/files/files/Teaching/GraduatesCourses/Metaheuristicas/Bibliography/VNS.pdf>`_, *Pierre Hansen, Nenad Mladenović*


.. image:: https://user-images.githubusercontent.com/43888615/118358101-95945d80-b57d-11eb-9e16-697cd1d8e930.png
	:width: 500
	:align: center

Pseudocode
^^^^^^^^^^^

.. code-block:: python

	def VNS():
		define_neighbourhood_strutures N_k (k=1,...,k_max)
		generateInitialSolution s ∈ S
		while (!stoppingCondition):
			k = 1
			while (k <= k_max):
				s' = shake(s), s' ∈ N_k(s)
				if (fitness(s'') < fitness(s)):
					s = s''
					k = 1
				else:
					k = k + 1

.. image:: https://user-images.githubusercontent.com/43888615/118771038-536f6280-b882-11eb-9e91-e505f82be8db.png
