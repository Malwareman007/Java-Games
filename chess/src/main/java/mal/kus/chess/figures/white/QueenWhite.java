package mal.kus.chess.figures.white;

import mal.kus.chess.figures.Cell;
import mal.kus.chess.figures.Figure;


public class QueenWhite implements Figure {
    private final Cell position;

    public QueenWhite(final Cell ps) {
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
        return new QueenWhite(dest);
    }
}
