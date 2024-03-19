module web.calcmathlab2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens web.calcmathlab2 to javafx.fxml;
    exports web.calcmathlab2;
}