package net.javacogito.numberoperations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;


public class Main {

  private static final int PLUS = 0;
  private static final int MINUS = 1;
  private static final int TIMES = 2;
  private static final int OVERFLOW = 3;
  private static final int OPERATION_COUNT = 4;
  private static final int NUMBERS_COUNT = 5;
  private static final int FAVORITE_NUMBER = 42;
  private static boolean isPossibleToReach = false;

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      int[] values = parseValues(inputLine);
      isPossibleToReach = false;
      permute(values, 0);
      if(isPossibleToReach){
        System.out.println("YES");
      } else {
        System.out.println("NO");
      }
    }
  }

  private static int[] parseValues(String inputLine){
    int[] result = new int[NUMBERS_COUNT];
    String[] rawData = inputLine.split(" ");
    for(int i = 0; i <= NUMBERS_COUNT - 1; i++){
      result[i] = Integer.parseInt(rawData[i]);
    }
    return result;
  }

  private static void permute(int[] values, int startIndex){
    if(isPossibleToReach) {
      return;
    }
    int length = values.length;
    if(startIndex == length - 1){
      isPossibleToReach = checkAllOperations(values);
      return;
    }
    for(int i = startIndex; i <= length - 1; i++){
      permute(values, startIndex + 1);
      values = rotate(values, startIndex);
    }
  }

  private static int[] rotate(int[] values, int startIndex){
    int length = values.length;
    int[] result = Arrays.copyOf(values, length);
    int temp = values[startIndex];
    for(int i = startIndex; i <= length - 2; i++){
      result[i] = result[i + 1];
    }
    result[length - 1] = temp;
    return result;
  }

  private static boolean checkAllOperations(int[] values){
    OperationSets operationSets = new OperationSets();
    int result = 0;
    for (int[] operationSet : operationSets){
       result = calculate(values, operationSet);
       if(result == FAVORITE_NUMBER){
         return true;
       }
    }
    return false;
  }

  private static int calculate(int[] values, int[] operationSet){
    int result = values[0];

    for (int i = 1; i <= OPERATION_COUNT; i++){
      result = calculate(result, values[i], operationSet[i - 1]);
    }

    return result;
  }

  private static int calculate(int value1, int value2, int operationCode){
    switch (operationCode){
      case PLUS: return  value1 + value2;
      case MINUS: return  value1 - value2;
      case TIMES: return  value1 * value2;
    }
    return 0;
  }


  private static class OperationSets implements Iterable<int[]>{
    private final int[] operationCodes = new int[OPERATION_COUNT];
    {operationCodes[0] = -1;}

    @Override
    public Iterator<int[]> iterator() {

      return new Iterator<int[]>() {
        @Override
        public boolean hasNext() {
          return !(operationCodes[0] == TIMES && operationCodes[1] == TIMES && operationCodes[2] == TIMES &&
            operationCodes[3] == TIMES);
        }
        @Override
        public int[] next() {
          for(int i = 0; i <= OPERATION_COUNT - 1; i++){
            operationCodes[i]++;
            if(operationCodes[i] == OVERFLOW){
              operationCodes[i] = PLUS;
            } else {
              break;
            }
          }
          return operationCodes;
        }
        @Override
        public void remove() {

        }
      };
    }
  }
}
