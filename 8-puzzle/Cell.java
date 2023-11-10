import java.util.Objects;

public class Cell {
    public int row;
    public int col;

    public Cell(int i, int j) {
        row = i;
        col = j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || (o instanceof Cell))
            return false;
        Cell cell = (Cell) o;
        return row == cell.row && col == cell.col;
    }

}
