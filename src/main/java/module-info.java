module fifa {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens fifa to javafx.fxml;
    exports fifa;
}
