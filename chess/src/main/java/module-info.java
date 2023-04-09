module chess {
    requires javafx.fxml;
    requires javafx.controls;
    opens mal.kus.chess to javafx.fxml;
    exports mal.kus.chess;
}