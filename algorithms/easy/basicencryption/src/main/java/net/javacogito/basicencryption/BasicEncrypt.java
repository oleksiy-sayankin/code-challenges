package net.javacogito.basicencryption;

public class BasicEncrypt {
  public String encrypt(String text, int rule) {
    StringBuilder sb = new StringBuilder();
    int length = text.length();
    for (int i = 0; i <= length - 1; i++) {
      sb.append(Character.toString(((text.charAt(i) + rule)) % 256));
    }
    return sb.toString();
  }
}
