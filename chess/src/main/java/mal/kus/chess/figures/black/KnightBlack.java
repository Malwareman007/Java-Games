package mal.kus.chess.figures.black;

import mal.kus.chess.figures.Cell;
import mal.kus.chess.figures.Figure;


public class KnightBlack implements Figure {
    private final Cell position;

    public KnightBlack(final Cell ps) {
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
        return new KnightBlack(dest);
    }
}
