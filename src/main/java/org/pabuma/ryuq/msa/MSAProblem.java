package org.pabuma.ryuq.msa;

import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.io.FastaReaderHelper;
import org.jmsa.score.Score;
import org.jmsa.score.impl.Entropy;
import org.jmsa.score.impl.PercentageOfTotallyConservedColumns;
import org.jmsa.score.impl.SumOfPairs;
import org.jmsa.substitutionmatrix.impl.GenericSubstitutionMatrix;
import org.uma.jmetal.problem.Problem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class MSAProblem implements Problem<MSASolution> {
  private List<StringBuilder> sequences ;
  private List<String> sequenceNames ;
  private List<Score> scores;

  public MSAProblem(String fastaFile, List<Score>scores) throws IOException {
    sequences = readDataFromFastaFile(fastaFile);
    sequenceNames = readSequenceNamesFromFastaFile(fastaFile) ;
    this.scores = scores ;
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
    char[][] decodedSequences = decodeSequences(solution.variables()) ;

    for (int i = 0; i < scores.size(); i++) {
      double scoreValue = scores.get(i).compute(decodedSequences) ;
      solution.objectives()[i] = -1 * scoreValue ;
    }

    return solution;
  }

  @Override
  public MSASolution createSolution() {
    return new MSASolution(sequences, scores.size());
  }

  private char[][] decodeSequences(List<StringBuilder> sequences) {
    char[][] decodedSequences;
    decodedSequences = new char[getNumberOfVariables()][sequences.get(0).length()] ;
    IntStream.range(0, sequences.size()).forEach(i -> decodedSequences[i] = sequences.get(i).toString().toCharArray());

    return decodedSequences;
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
      sequenceNameList.add(new String(entry.getValue().getOriginalHeader()));
    }

    return sequenceNameList;
  }


  public static void main(String[] args) throws IOException {
    MSAProblem msa = new MSAProblem("resources/BB11001.fasta",
            List.of(new PercentageOfTotallyConservedColumns(), new Entropy(),
                    new SumOfPairs(new GenericSubstitutionMatrix("resources/PAM250Matrix")))) ;

    System.out.println(msa.sequenceNames) ;
    System.out.println(msa.sequences) ;

    MSASolution solution = msa.createSolution() ;
    msa.evaluate(solution) ;
    int a = 0 ;
    System.out.println(solution) ;
  }
}
