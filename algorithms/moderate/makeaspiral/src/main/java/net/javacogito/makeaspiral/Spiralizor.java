package net.javacogito.makeaspiral;

public class Spiralizor {

  private enum DIRECTION {RIGHT, DOWN, LEFT, UP}

  private static class Coordinate {
    private int i, j;
    private final int[][] spiral;
    private final int length;

    private DIRECTION direction = DIRECTION.RIGHT;

    public Coordinate(int[][] spiral) {
      this.i = 0;
      this.j = 0;
      this.spiral = spiral;
      this.length = spiral.length;
      use(0, 0);
    }

    private void use(int i, int j) {
      spiral[i][j] = 1;
    }

    private boolean isUsed(int i, int j) {
      return spiral[i][j] == 1;
    }

    private boolean existsAndUsed(int i, int j) {
      return exists(i, j) && isUsed(i, j);
    }

    private boolean existsAndEmpty(int i, int j) {
      return exists(i, j) && !isUsed(i, j);
    }

    private boolean exists(int i, int j) {
      return i >= 0 && i <= length - 1 && j >= 0 && j <= length - 1;
    }

    boolean canMove() {
      switch (direction) {
        case RIGHT -> {
          if (existsAndUsed(i, j + 2) && existsAndUsed(i + 2, j)) {
            return false;
          }
        }
        case DOWN -> {
          if (existsAndUsed(i + 2, j) && existsAndUsed(i, j - 2)) {
            return false;
          }
        }
        case LEFT -> {
          if (existsAndUsed(i, j - 2) && existsAndUsed(i - 2, j)) {
            return false;
          }
        }
        case UP -> {
          if (existsAndUsed(i - 2, j) && existsAndUsed(i, j + 2)) {
            return false;
          }
        }
      }
      return true;
    }

    void move() {
      switch (direction) {
        case RIGHT -> {
          if (existsAndEmpty(i, j + 1) && existsAndUsed(i, j + 3)) {
            j++;
            use(i, j);
            direction = DIRECTION.DOWN;
            break;
          }
          if (existsAndEmpty(i, j + 1) && existsAndEmpty(i, j + 2)) {
            j++;
            use(i, j);
            break;
          }
          if (existsAndEmpty(i, j + 1) && j == length - 2) {
            j++;
            use(i, j);
            direction = DIRECTION.DOWN;
          }
        }
        case DOWN -> {
          if (existsAndEmpty(i + 1, j) && existsAndUsed(i + 3, j)) {
            i++;
            use(i, j);
            direction = DIRECTION.LEFT;
            break;
          }
          if (existsAndEmpty(i + 1, j) && existsAndEmpty(i + 2, j)) {
            i++;
            use(i, j);
            break;
          }
          if (existsAndEmpty(i + 1, j) && i == length - 2) {
            i++;
            use(i, j);
            direction = DIRECTION.LEFT;
          }
        }
        case LEFT -> {
          if (existsAndEmpty(i, j - 1) && existsAndUsed(i, j - 3)) {
            j--;
            use(i, j);
            direction = DIRECTION.UP;
            break;
          }
          if (existsAndEmpty(i, j - 1) && existsAndEmpty(i, j - 2)) {
            j--;
            use(i, j);
            break;
          }
          if (existsAndEmpty(i, j - 1) && j == 1) {
            j--;
            use(i, j);
            direction = DIRECTION.UP;
          }
        }
        case UP -> {
          if (existsAndEmpty(i - 1, j) && existsAndUsed(i - 3, j)) {
            i--;
            use(i, j);
            direction = DIRECTION.RIGHT;
            break;
          }

          if (existsAndEmpty(i - 1, j) && existsAndEmpty(i - 2, j)) {
            i--;
            use(i, j);
          }
        }
      }
    }
  }

  public static int[][] spiralize(int size) {
    int[][] spiral = new int[size][size];
    Coordinate coordinate = new Coordinate(spiral);
    while (coordinate.canMove()) {
      coordinate.move();
    }
    print(spiral);
    return spiral;
  }

  private static void print(int[][] spiral) {
    for (int i = 0; i <= spiral.length - 1; i++) {
      for (int j = 0; j <= spiral.length - 1; j++) {
        System.out.print(spiral[i][j]);
      }
      System.out.println();
    }
  }
}
