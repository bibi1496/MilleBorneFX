package Graphics;

import Graphics.vue.PanneauJeu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Javafx extends Application{

    /**
     * Stock le panneau de jeu.
     */
    public static PanneauJeu panneau = new PanneauJeu();

    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(panneau.lancer(), 1200, 900);
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:assets/logoMilleBorne.png"));
        stage.show();
    }
}



