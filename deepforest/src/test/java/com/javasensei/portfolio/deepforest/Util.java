package com.javasensei.portfolio.deepforest;

import com.javasensei.portfolio.math.Point;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * @author oleksiy sayankin
 */
public final class Util {
    private Util(){}
    /**
     * Empty and delete a folder (and subfolders).
     * @param folder
     *            folder to empty
     */
    public static void rmdir(final File folder) {
        // check if folder file is a real folder
        if (folder.isDirectory()) {
            File[] list = folder.listFiles();
            if (list != null) {
                for (int i = 0; i < list.length; i++) {
                    File tmpF = list[i];
                    if (tmpF.isDirectory()) {
                        rmdir(tmpF);
                    }
                    tmpF.delete();
                }
            }
            if (!folder.delete()) {
                System.out.println("can't delete folder : " + folder);
            }
        }
    }

    public static OutputData read(Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);

        OutputData outputData = new OutputData();
        try{
            String firstLine = br.readLine();
            if(Constants.YES.equals(firstLine.trim().toUpperCase())){
                outputData. setForestIsDeep(true);
            } else {
                outputData. setForestIsDeep(false);
                String secondLine =  br.readLine();
                String[] values = secondLine.split(" ");
                double x = Double.valueOf(values[0]);
                double y = Double.valueOf(values[1]);
                outputData.setExit(new Point(x, y));
            }
        }   finally {
            br.close();
        }
        return outputData;
    }
}
