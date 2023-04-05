package net.javacogito.snailsort;

import java.util.ArrayList;
import java.util.List;

public class Snail {
  private enum Direction {
    RIGHT, DOWN, LEFT, UP;

    public Direction next(Direction current) {
      return switch (current) {
        case RIGHT -> DOWN;
        case DOWN -> LEFT;
        case LEFT -> UP;
        case UP -> RIGHT;
      };
    }
  }

  private static class Field {
    private int y = 0;
    private int x = 0;
    private final boolean[][] isEaten;
    private final int side;

    private Direction direction = Direction.RIGHT;

    public Field(int side) {
      this.side = side;
      isEaten = new boolean[side][];
      for (int i = 0; i <= side - 1; i++) {
        isEaten[i] = new boolean[side];
      }
    }

    public void iterate() {
      if (!hasWhatToEat()) {
        return;
      }
      eat();
      switch (direction) {
        case RIGHT -> {
          if (x <= side - 2 && !isEaten[y][x + 1]) {
            x++;
            return;
          }
          if (x == side - 1 || (x <= side - 2 && isEaten[y][x + 1])) {
            nextDirection();
            iterate();
          }
        }
        case DOWN -> {
          if (y <= side - 2 && !isEaten[y + 1][x]) {
            y++;
            return;
          }
          if (y == side - 1 || (y <= side - 2 && isEaten[y + 1][x])) {
            nextDirection();
            iterate();
          }
        }
        case LEFT -> {
          if (x >= 1 && !isEaten[y][x - 1]) {
            x--;
            return;
          }
          if (x == 0 || (x >= 1 && isEaten[y][x - 1])) {
            nextDirection();
            iterate();
          }
        }
        case UP -> {
          if (y >= 1 && !isEaten[y - 1][x]) {
            y--;
            return;
          }
          if (y == 0 || (y >= 1 && isEaten[y - 1][x])) {
            nextDirection();
            iterate();
          }
        }
      }
    }

    private void eat() {
      isEaten[y][x] = true;
    }

    private void nextDirection() {
      direction = direction.next(direction);
    }

    public boolean hasWhatToEat() {
      boolean result = false;
      for (boolean[] line : isEaten) {
        for (boolean isEaten : line) {
          if (!isEaten) {
            return true;
          }
        }
      }
      return result;
    }

    public int getY() {
      return y;
    }

    public int getX() {
      return x;
    }
  }

  public static int[] snail(int[][] array) {
    if(array.length == 1 && array[0].length == 0) {
      return new int[0];
    }
    List<Integer> result = new ArrayList<>();
    Field field = new Field(array.length);
    while (field.hasWhatToEat()) {
      result.add(array[field.getY()][field.getX()]);
      field.iterate();
    }
    return toArray(result);
  }

  public static int[] toArray(List<Integer> integers) {
    int size = integers.size();
    int[] result = new int[size];
    for (int i = 0; i <= size - 1; i++) {
      result[i] = integers.get(i);
    }
    return result;
  }
}
