package mal.kus.chess;

public class ImpossibleMoveException extends RuntimeException {
    public ImpossibleMoveException(String message) {
        super(message);
    }
}
