package net.javacogito.labyrinth;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * @author oleksiy sayankin
 */
public final class Util {
    private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);

    @Before
    public void init(){
        PropertyConfigurator.configure("log4j.properties");
    }

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
                LOGGER.info("can't delete folder : " + folder);
            }
        }
    }

    public static String readFirstStringFromFile(Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        String firstLine;
        try{
            firstLine = br.readLine();
        }   finally {
           br.close();
        }
        return firstLine;
    }

}
