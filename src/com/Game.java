import java.awt.*;
import java.util.ArrayList;

class Game {
    Integer[][] values;
    int size = 4;
    int spacing = 4;

    public Game() {
        values = new Integer[size][size];
        addStartingTiles();
    }

    private void addStartingTiles() {
        for (int n = 0; n < 2; n++) {
            addTile();
        }
    }

    private void addTile() {
        if (existsFreeSquare()) {
            int newValue = (Math.random() < 0.9) ? 2 : 4;
            ArrayList<Point> freeSquares = freeSquares();
            int numFree = freeSquares.size();
            Point location = freeSquares.get((int) Math.floor(Math.random() * numFree));
            values[location.x][location.y] = newValue;
        }
    }

    private ArrayList<Point> freeSquares() {
        ArrayList<Point> freeSquares = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (values[i][j] == null) {
                    freeSquares.add(new Point(i, j));
                }
            }
        }
        return freeSquares;
    }

    public void move(int dir) {
        // dir = 0 -> up, 1 -> right, 2 -> down, 3 -> left
        // TODO: refactor each directions move/move_aux to a more general function
        switch (dir) {
            case 0:
                move_up();
                break;
            case 1:
                move_right();
                break;
            case 2:
                move_down();
                break;
            case 3:
                move_left();
                break;
        }
        addTile();

    }

    private void move_up() {
        for (int col = 0; col < size; col++) {
            move_up_aux(col);
        }
    }

    private void move_up_aux(int col) {
        for (int currCheck = 0; currCheck < size; currCheck++) {
            if (values[currCheck][col] == null) {
                continue;
            }
            int compare = currCheck - 1;
            int distToMove = 0;
            while (compare >= 0) {
                if (values[compare][col] == null) {
                    distToMove++;
                } else if (values[compare][col].equals(values[currCheck][col])) {
                    values[compare][col] *= 2;
                    values[currCheck][col] = null;
                    distToMove = 0;
                    break;
                }
                compare--;
            }
            if (distToMove > 0) {
                values[currCheck - distToMove][col] = values[currCheck][col];
                values[currCheck][col] = null;
            }
        }
    }

    private void move_down() {
        for (int col = 0; col < size; col++) {
            move_down_aux(col);
        }
    }

    private void move_down_aux(int col) {
        for (int currCheck = size - 1; currCheck >= 0; currCheck--) {
            if (values[currCheck][col] == null) {
                continue;
            }
            int compare = currCheck + 1;
            int distToMove = 0;
            while (compare < size) {
                if (values[compare][col] == null) {
                    distToMove++;
                } else if (values[compare][col].equals(values[currCheck][col])) {
                    values[compare][col] *= 2;
                    values[currCheck][col] = null;
                    distToMove = 0;
                    break;
                }
                compare++;
            }
            if (distToMove > 0) {
                values[currCheck + distToMove][col] = values[currCheck][col];
                values[currCheck][col] = null;
            }
        }
    }

    private void move_left() {
        for (int row = 0; row < size; row++) {
            move_left_aux(row);
        }
    }

    private void move_left_aux(int row) {
        for (int currCheck = 0; currCheck < size; currCheck++) {
            if (values[row][currCheck] == null) {
                continue;
            }
            int compare = currCheck - 1;
            int distToMove = 0;
            while (compare >= 0) {
                if (values[row][compare] == null) {
                    distToMove++;
                } else if (values[row][compare].equals(values[row][currCheck])) {
                    values[row][compare] *= 2;
                    values[row][currCheck] = null;
                    distToMove = 0;
                    break;
                }
                compare--;
            }
            if (distToMove > 0) {
                values[row][currCheck - distToMove] = values[row][currCheck];
                values[row][currCheck] = null;
            }
        }
    }

    private void move_right() {
        for (int row = 0; row < size; row++) {
            move_right_aux(row);
        }
    }

    private void move_right_aux(int row) {
        for (int currCheck = size - 1; currCheck >= 0; currCheck--) {
            if (values[row][currCheck] == null) {
                continue;
            }
            int compare = currCheck + 1;
            int distToMove = 0;
            while (compare < size) {
                if (values[row][compare] == null) {
                    distToMove++;
                } else if (values[row][compare].equals(values[row][currCheck])) {
                    values[row][compare] *= 2;
                    values[row][currCheck] = null;
                    distToMove = 0;
                    break;
                }
                compare++;
            }
            if (distToMove > 0) {
                values[row][currCheck + distToMove] = values[row][currCheck];
                values[row][currCheck] = null;
            }
        }
    }

    private boolean existsFreeSquare() {
        for (Integer[] i : values) {
            for (Integer j : i) {
                if (j == null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result.append("|");
                String value = (values[i][j] == null) ? "_".repeat(spacing) : prettyInt(values[i][j]);
                result.append(value).append("|");
                result.append(" ".repeat(2));
            }
            result.append("\n");
        }
        return result.toString();
    }

    private String prettyInt(int i) {
        StringBuilder result = new StringBuilder();
        String strI = String.valueOf(i);
        int len = strI.length();
        // adding spacing to short strings
        if (len < spacing) {
            result.append(" ".repeat(spacing - len)).append(strI);
        } else if (len > spacing) {
            result.append(strI, 0, spacing);
        } else {
            result.append(strI);
        }
        return result.toString();
    }

    public boolean gameOver() {
        return !existsFreeSquare();
    }
}