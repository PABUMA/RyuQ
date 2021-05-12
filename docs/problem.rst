Multiple sequence alignment
===========================

A multiple sequence alignment (MSA) is an alignment of more than
two sequences. The most common applications are:

- phylogenetic reconstruction

- structural analysis of proteins

- the search for retained domains

In all cases, the multiple alignment algorithms assume
the sequences we're aligning are descended from a common ancestor
and what we're trying to do is align the counterpart positions.
Multiple alignment algorithms are designed to align sequences fairly
different from each other.

The problem of multiple alignment is more computationally complex
than the alignment by pairs of sequences. In this case it is not
possible to apply an algorithm equivalent to Smith Waterman's for couples
of sequences that guarantees us an optimal result given a scoring scheme
for changes and gaps. This algorithm would require building and exploring a matrix of
n-dimensions (one for each sequence) which would require a time that would grow
exponentially with the number and length of sequences. Given the impossibility
of such a method, no usual program implements it and all resort to
algorithms that use different heuristic methods to simplify the problem.
This implies that each method will assume certain characteristics in the sequences
and that he will give up on correctly aligning certain types of sequences.

In short, the alignment procedure consists in inserting gaps inside the sequences:

.. figure:: /resources/images/Captura.PNG
