package org.pabuma.ryuq.msa;

        import org.biojava.nbio.core.sequence.ProteinSequence;
        import org.biojava.nbio.core.sequence.io.FastaReaderHelper;
        import org.biojava.nbio.core.sequence.io.FastaWriterHelper;
        import org.jmsa.score.Score;
        import org.uma.jmetal.problem.Problem;

        import java.io.File;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.LinkedHashMap;
        import java.util.List;
        import java.util.Map;
        import java.util.stream.IntStream;

/**
 * Class representing multiple sequence alignment (MSA) optimization problems. The data defining the
 * MSA is read from a Fasta file, and it is assumed that the MSA is aligned.
 */
public class MSAProblem implements Problem<MSASolution> {
  private List<StringBuilder> sequences;
  private List<String> sequenceNames;
  private List<Score> scores;

  public MSAProblem(String fastaFile, List<Score> scores) throws IOException {
    sequences = readDataFromFastaFile(fastaFile);
    sequenceNames = readSequenceNamesFromFastaFile(fastaFile);
    this.scores = scores;
  }

  @Override
  public int getNumberOfVariables() {
    return sequences.size();
  }

  @Override
  public int getNumberOfObjectives() {
    return scores.size();
  }

  @Override
  public int getNumberOfConstraints() {
    return 0;
  }

  @Override
  public String getName() {
    return "Multiple sequence alignment problem";
  }

  @Override
  public MSASolution evaluate(MSASolution solution) {
    solution.removeGapColumns();
    char[][] decodedSequences = decodeSequences(solution.variables());

    for (int i = 0; i < scores.size(); i++) {
      double scoreValue = scores.get(i).compute(decodedSequences);
      solution.objectives()[i] = -1 * scoreValue;
    }

    return solution;
  }

  @Override
  public MSASolution createSolution() {
    return new MSASolution(sequences, scores.size());
  }

  private char[][] decodeSequences(List<StringBuilder> sequences) {
    char[][] decodedSequences;
    decodedSequences = new char[getNumberOfVariables()][sequences.get(0).length()];
    IntStream.range(0, sequences.size()).forEach(i -> decodedSequences[i] = sequences.get(i).toString().toCharArray());

    return decodedSequences;
  }

  public void writeSequencesToFasta(List<StringBuilder> sequences, String fileName) throws Exception {
    List<ProteinSequence> proteinSequences = new ArrayList<>();
    for (StringBuilder sequence : sequences) {
      proteinSequences.add(new ProteinSequence(sequence.toString()));
    }

    for (int i = 0; i < sequenceNames.size(); i++) {
      proteinSequences.get(i).setOriginalHeader(sequenceNames.get(i));
    }
    FastaWriterHelper.writeProteinSequence(new File(fileName), proteinSequences);
  }

  public List<StringBuilder> readDataFromFastaFile(String dataFile) throws IOException {

    List<StringBuilder> sequenceList = new ArrayList<>();

    LinkedHashMap<String, ProteinSequence>
            sequences = FastaReaderHelper.readFastaProteinSequence(new File(dataFile));

    for (Map.Entry<String, ProteinSequence> entry : sequences.entrySet()) {
      sequenceList.add(new StringBuilder(entry.getValue().getSequenceAsString()));
    }

    return sequenceList;
  }

  private List<String> readSequenceNamesFromFastaFile(String fastaFile) throws IOException {
    List<String> sequenceNameList = new ArrayList<>();

    LinkedHashMap<String, ProteinSequence>
            sequences = FastaReaderHelper.readFastaProteinSequence(new File(fastaFile));

    for (Map.Entry<String, ProteinSequence> entry : sequences.entrySet()) {
      sequenceNameList.add(entry.getValue().getOriginalHeader());
    }

    return sequenceNameList;
  }

  public void printMSAScores(MSASolution solution, List<Score> scores) {
    char[][] decodedSolution = decodeSequences(solution.variables());
    System.out.println("\nSCORES") ;
    for (Score score : scores) {
      System.out.println(score.name() + ": " + score.compute(decodedSolution));
    }
  }
}