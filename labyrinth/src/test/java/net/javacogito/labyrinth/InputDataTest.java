package net.javacogito.labyrinth;

import org.junit.Test;
import org.junit.Assert;
import java.io.InputStreamReader;

/**
 * @author oleksiy sayankin
 */
public class InputDataTest {

    @Test
    public void readTest() throws Exception {

        InputData.getInstance().read(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("data001.in")));
        Labyrinth expectedLabyrinth = new Labyrinth(13, 10);
        expectedLabyrinth.addRow(0, "1111111111");
        expectedLabyrinth.addRow(1, "1000010000");
        expectedLabyrinth.addRow(2, "1000010001");
        expectedLabyrinth.addRow(3, "1000010001");
        expectedLabyrinth.addRow(4, "1000010001");
        expectedLabyrinth.addRow(5, "1000010001");
        expectedLabyrinth.addRow(6, "1000010001");
        expectedLabyrinth.addRow(7, "1000010001");
        expectedLabyrinth.addRow(8, "1000010001");
        expectedLabyrinth.addRow(9, "1000010001");
        expectedLabyrinth.addRow(10, "1000010001");
        expectedLabyrinth.addRow(11, "0011110001");
        expectedLabyrinth.addRow(12, "1111111111");
        Coord expextedInitPos = new Coord(3, 4);
        Labyrinth actualLabyrinth = InputData.getInstance().getLabyrinth();
        Coord actualInitPos = InputData.getInstance().getInitPos();
        Assert.assertEquals(expectedLabyrinth, actualLabyrinth);
        Assert.assertEquals(expextedInitPos, actualInitPos);
    }
}
