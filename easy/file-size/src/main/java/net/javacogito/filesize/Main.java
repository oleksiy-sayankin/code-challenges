package net.javacogito.filesize;

import java.io.File;
import java.io.IOException;


public class Main {

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    System.out.println(file.length());
  }
}