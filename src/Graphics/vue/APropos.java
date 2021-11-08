package Graphics.vue;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Contient le code l'alert pour A propos.
 */
public class APropos {

    /**
     * Alert A propos.
     * @return Une alert avec les bonnes propriétés.
     */
    public static Alert getAPropos() {
        ImageView icone = new ImageView("file:assets/boite.jpg");
        icone.setPreserveRatio(true);
        icone.setFitWidth(400);
        ButtonType button1 = new ButtonType("Ok");
        Alert diag = new Alert(Alert.AlertType.NONE, "AlertPrincipale", button1);
        VBox content = new VBox(icone,new Label("Réalisé par Baptiste et Gaetan."));
        content.setAlignment(Pos.CENTER);
        diag.setHeaderText(null);
        diag.getDialogPane().setContent(content);
        diag.setTitle("A propos");
        return diag;
    }
}


