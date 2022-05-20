module fifa {
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens fifa to javafx.fxml;
    exports fifa;
}
