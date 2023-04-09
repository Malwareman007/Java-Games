package mal.kus.chess.figures.white;

import mal.kus.chess.figures.Cell;
import mal.kus.chess.figures.Figure;

public class KingWhite implements Figure {
    private final Cell position;

    public KingWhite(final Cell ps) {
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
        return new KingWhite(dest);
    }
}
