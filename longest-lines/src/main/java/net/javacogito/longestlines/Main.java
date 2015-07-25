package net.javacogito.longestlines;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main( String[] args ) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String inputLine;
        int count = Integer.parseInt(buffer.readLine());
        TreeSet<String> lines = new TreeSet<String>(new LengthComp());
        while ((inputLine = buffer.readLine()) != null) {
            inputLine = inputLine.trim();
            lines.add(inputLine);
        }
        NavigableSet<String>  descendingLines = lines.descendingSet();
        int i = 1;
        for (String line : descendingLines){
            if (i <= count){
                System.out.println(line);
            }
            i++;
        }
    }
    static class LengthComp implements Comparator<String>{
        public int compare(String e1, String e2) {
            return e1.length() - e2.length();
        }
    }
}
