package net.javacogito.slangflavor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  private static final String[] SLANG_WORDS = {", yeah!", ", this is crazy, I tell ya.", ", can U believe this?",
  ", eh?", ", aw yea.", ", yo.", "? No way!", ". Awesome!"};

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    StringBuilder sb = new StringBuilder();
    while ((inputLine = buffer.readLine()) != null) {
        sb.append(inputLine);
        sb.append("\n");
    }
    System.out.println(makeSlang(sb.toString()));
  }

  private static String makeSlang(String data) {
    int markPosition = findMarkPosition(data, 0);
    int slangIndex = 0;
    int slangAmount = SLANG_WORDS.length;
    boolean isEven = true;
    while (markPosition > 0){
      markPosition = findMarkPosition(data, markPosition + 1);
      if(markPosition > 0){
        if(!isEven){
          isEven = true;
          continue;
        }
        isEven = false;
        data = replaceWithSlangAt(data, markPosition, slangIndex);
        markPosition += SLANG_WORDS[slangIndex].length();
        slangIndex++;
        if (slangIndex == slangAmount){
          slangIndex = 0;
        }
      }
    }
    return data;
  }

  private static String replaceWithSlangAt(String input, int markPosition, int slangIndex){
    return input.substring(0, markPosition) + SLANG_WORDS[slangIndex] + input.substring(markPosition + 1);
  }

  private static int findMarkPosition(String data, int fromIndex){
    int periodIndex = data.indexOf(".", fromIndex);
    int exclamationIndex = data.indexOf("!", fromIndex);
    int questionIndex = data.indexOf("?", fromIndex);
    periodIndex = periodIndex > 0 ? periodIndex : Integer.MAX_VALUE;
    exclamationIndex = exclamationIndex > 0 ? exclamationIndex : Integer.MAX_VALUE;
    questionIndex = questionIndex > 0 ? questionIndex : Integer.MAX_VALUE;
    int min = Math.min(periodIndex, Math.min(exclamationIndex, questionIndex));
    return min == Integer.MAX_VALUE ? -1 : min;

  }
}