package mal.kus.chess.figures.white;

import mal.kus.chess.figures.Cell;
import mal.kus.chess.figures.Figure;


public class KnightWhite implements Figure {
    private final Cell position;

    public KnightWhite(final Cell ps) {
        position = ps;
    }

    @Override
    public Cell position() {
        return position;
    }

    @Override
    public Cell[] way(Cell dest) {
        return new Cell[] {
                dest
        };
    }

    @Override
    public Figure copy(Cell dest) {
        return new KnightWhite(dest);
    }
}
