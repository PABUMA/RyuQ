Performance evaluation
======================

To assess the performance of the implemented algorithms, we are going to conduct an experimentation process consisting in
performing ten independent runs per algorithm by using as target problem the BB11001 instance which has been
previously aligned by using Clustal Omega (file `BB11001.tfa_clu`, located in the resources folder). To ensure that
the comparison is fair, all the algorithms are configured to stop when  1 million of evaluation as been computed.

+-----+------+------+
| Run |  LS  |  SA  |
+=====+======+======+
|  1  |  31  |  34  |
+-----+------+------+
|  2  |  34  |  29  |
+-----+------+------+
|  3  |  27  |  27  |
+-----+------+------+
|  4  |  34  |  30  |
+-----+------+------+
|  5  |  45  |  31  |
+-----+------+------+
|  6  |  28  |  43  |
+-----+------+------+
|  7  |  26  |  25  |
+-----+------+------+
|  8  |  40  |  26  |
+-----+------+------+
|  9  |  34  |  27  |
+-----+------+------+
| 10  |  31  |  47  |
+-----+------+------+


.. code-block:: python

	import scipy.stats as stats
    import matplotlib.pyplot as plt

    local_search_fitness = [31,34,27,34,45,28,26,40,34,31]
    simulated_annealing_fitness = [34,29,27,30,31,43,25,26,27,47]

    plt.boxplot([local_search_fitness, simulated_annealing_fitness], labels=["LS", "SA"])
    plt.show()

    # https://www.statology.org/mann-whitney-u-test-python/
    print(stats.mannwhitneyu(local_search_fitness, simulated_annealing_fitness))

.. image:: figures/boxplots.png
