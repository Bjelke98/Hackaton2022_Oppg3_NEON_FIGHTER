package cell;

import game.Settings;

import java.util.Objects;

public record Point(int x, int y) {

    public double sx(){
        return x*Settings.CELL_SIZE;
    }
    public double sy(){
        return y*Settings.CELL_SIZE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x+", "+y;
    }
}
