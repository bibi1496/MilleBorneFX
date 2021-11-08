module JavaFx2 {
    requires com.google.gson;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    exports Graphics.modele.mille_bornes;
    exports Graphics;
    exports Graphics.controlleur;
}