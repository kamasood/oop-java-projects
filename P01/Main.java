package P01;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
  /**
   * main method creates class instance and calls .run method
   */
  public static void main(String[] args){
    Main mainObject = new Main();
    mainObject.run();
  }

  private void run() {
    // read sequence from input .txt file
    ArrayList<Integer> sequence = new ArrayList<Integer>();
    sequence = readSequence("p01-in.txt");

    // declare count accumulator lists
    ArrayList<Integer> runsUpList = new ArrayList<Integer>();
    ArrayList<Integer> runsDownList = new ArrayList<Integer>();
    ArrayList<Integer> runsTotalList = new ArrayList<Integer>();

    // call methods to count runs
    runsUpList = this.runsUp(sequence);
    runsDownList = this.runsDown(sequence);

    // count total runs and combine lists
    int runsTotal = 0;
    for (int i = 0; i < sequence.size(); i++) {
      int runsAtIndex = runsUpList.get(i) + runsDownList.get(i);
      runsTotal += runsAtIndex;
      runsTotalList.add(runsAtIndex);
    }

    // output runs data to .txt file
    writeSequence("p01-runs.txt", runsTotal, runsTotalList);
  }

  /**
   * method to read sequence from an input file (with exception handler)
   */
  private ArrayList<Integer> readSequence(String pFilename) {
    ArrayList<Integer> sequence = new ArrayList<Integer>();
    try {
      File input = new File(pFilename);
      Scanner in = new Scanner(input);
      while(in.hasNextInt()){
        sequence.add(in.nextInt());
      }
      in.close();
    } catch(FileNotFoundException pExcept) {
      System.out.println("Oops, could not open 'p01-txt.in' for reading. The program is ending.");
      System.exit(-100);
    }
    return sequence;
  }

  /**
   * method to write sequence to an output file (with exception handler)
   */
  private void writeSequence(String pFilename, int pRunsTotal, ArrayList<Integer> pRunsTotalList) {
    try {
      File output = new File(pFilename);
      PrintWriter out = new PrintWriter(output);
      out.println("runs_total: " + pRunsTotal);
      for (int i = 1; i < pRunsTotalList.size(); i++) {
        out.println("runs_" + i + ": " + pRunsTotalList.get(i));
      }
      out.close();
    } catch(FileNotFoundException pExcept) {
      System.out.println("Oops, could not open 'p01-runs.txt' for writing. The program is ending.");
      System.exit(-200);
    }
  }

  /**
   * method to initialize an ArrayList with size and values
   */
  private ArrayList<Integer> arrayListCreate(int pSize, int pInitValue) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < pSize; i++) {
      list.add(pInitValue);
    }
    return list;
  }

  /**
   * method to return a list of run up counts
   */
  private ArrayList<Integer> runsUp(ArrayList<Integer> pSequence) {
    ArrayList<Integer> runsUpList = new ArrayList<Integer>();
    runsUpList = arrayListCreate(pSequence.size(), 0);

    int sequenceLength = 0;
    for (int i = 0; i < pSequence.size() - 1; i++) {
      if (pSequence.get(i) <= pSequence.get(i + 1)) {
        sequenceLength += 1;
      } else if (sequenceLength != 0) {
        runsUpList.set(sequenceLength, runsUpList.get(sequenceLength) + 1);
        sequenceLength = 0;
      }
    }
    if (sequenceLength != 0) {
      runsUpList.set(sequenceLength, runsUpList.get(sequenceLength) + 1);
    }
    return runsUpList;
  }

  /**
   * method to return a list of run down counts
   */
  private ArrayList<Integer> runsDown(ArrayList<Integer> pSequence) {
    ArrayList<Integer> runsDownList = new ArrayList<Integer>();
    runsDownList = arrayListCreate(pSequence.size(), 0);

    int sequenceLength = 0;
    for (int i = 0; i < pSequence.size() - 1; i++) {
      if (pSequence.get(i) >= pSequence.get(i + 1)) {
        sequenceLength += 1;
      } else if (sequenceLength != 0) {
        runsDownList.set(sequenceLength, runsDownList.get(sequenceLength) + 1);
        sequenceLength = 0;
      }
    }
    if (sequenceLength != 0) {
      runsDownList.set(sequenceLength, runsDownList.get(sequenceLength) + 1);
    }
    return runsDownList;
  }
}
