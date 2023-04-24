package net.javacogito.countthesmileyfaces;

import java.util.List;

public class SmileFaces {
  public static int countSmileys(List<String> faces) {
    int result = 0;
    for (String face : faces) {
      if (hasSmilingMouth(face) && eyesGoFirst(face) && hasValidNose(face)) {
        result++;
      }
    }
    return result;
  }

  private static boolean hasSmilingMouth(String face) {
    return face.contains(")") || face.contains("D");
  }

  private static boolean eyesGoFirst(String face) {
    return face.indexOf(":") == 0 || face.indexOf(";") == 0;
  }

  private static boolean hasValidNose(String face) {
    String noEyesNoMouth = face.substring(1, face.length() - 1);
    return noEyesNoMouth.isEmpty() || noEyesNoMouth.length() == 1 && (noEyesNoMouth.contains("-") || noEyesNoMouth.contains("~"));
  }
}
