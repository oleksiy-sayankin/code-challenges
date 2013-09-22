package com.javacogito.portfolio.anagrammer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;

/**
 * @author oleksiy sayankin
 */
public class OutputData {
    private int numberOfSequences;
    private String[] sequences;

    public void read(Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        try {
            numberOfSequences = Integer.parseInt(br.readLine());
            sequences = new String[numberOfSequences];
            for(int i = 0; i <= numberOfSequences - 1; i++){
                sequences[i] = br.readLine();
            }
        } finally {
            br.close();
        }
    }


    public void write(Writer writer) throws IOException {
        try {
            writer.write(numberOfSequences);
            writer.write("\n");
            for(String sequence : sequences){
                writer.write(sequence);
            }
            writer.flush();
        } finally {
            writer.close();
        }
    }




    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof OutputData)) {
            return false;
        }
        if (this == other) {
            return true;
        }
        OutputData otherOutputData = (OutputData) other;
        return this.numberOfSequences == otherOutputData.numberOfSequences && Arrays.equals(this.sequences, otherOutputData.sequences);
    }

    @Override
    public int hashCode() {
        return numberOfSequences + Arrays.hashCode(sequences);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(numberOfSequences);
        sb.append(", {");
        boolean first = true;
        for(String sequence : sequences){
            if(first){
                first = false;
            }else {
                sb.append(",");
            }
            sb.append(sequence);
        }
        sb.append("}]");
        return sb.toString();
    }
}
