package net.javacogito.anagrammer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*Thanks to Roman Slyusarchuk*/

public class Main {
    private static int sequenceCount;
    private static List<String> sequences = new ArrayList<String>();
    private static final int MAX_SEQUENCE_COUNT = 1000;
    private static int sequenceSize = 0;

    public static void main( String[] args ) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String sourceString = buffer.readLine();
        String destString = buffer.readLine();
        sequenceSize = sourceString.length() * 2;
        int sourceIndex = 0;
        int destIndex = 0;
        Stack<String> currentString = new Stack<String>();
        Stack<Integer> sequence = new Stack<Integer>();
        findSequence(sourceString, destString, sourceIndex, destIndex, currentString, sequence);
        System.out.println(sequenceCount);
        if (sequenceCount <= MAX_SEQUENCE_COUNT){
            printSequences();
        }
    }

    public static void findSequence(String sourceString, String destString, int sourceIndex, int destIndex, Stack<String> currentString, Stack<Integer> sequence){
        if(sequence.size() == sequenceSize){
            sequenceCount++;
            if (sequenceCount <= MAX_SEQUENCE_COUNT){
                sequences.add(stackToString(sequence));
            }
            return;
        }
        if(sourceIndex < sourceString.length()){
            currentString.push(stringAt(sourceString, sourceIndex));
            sequence.push(1);
            findSequence(sourceString, destString, sourceIndex + 1, destIndex, currentString, sequence);
            sequence.pop();
            currentString.pop();
        }
        if(currentString.size() > 0 && currentString.peek().equals(stringAt(destString, destIndex))){
            String s = currentString.pop();
            sequence.push(2);
            findSequence(sourceString, destString, sourceIndex, destIndex + 1, currentString, sequence);
            sequence.pop();
            currentString.push(s);
        }
    }


    public static String stackToString(Stack<Integer> r){
        StringBuilder sb = new StringBuilder();
        for (Integer element : r){
            sb.append(element);
        }
        return sb.toString();
    }

    public static String stringAt(String source, int index){
        return source.substring(index, index + 1);
    }

    public static void printSequences(){

        for(String line : sequences){
            System.out.println(line);
        }
    }
}
