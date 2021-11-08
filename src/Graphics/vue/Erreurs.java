package Graphics.vue;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Contient le code des alert pour les erreurs.
 */
public class Erreurs {
    /**
     * Alert pour les erreurs que font les joueurs dans le jeu.
     * @param erreur à afficher.
     * @return Une alert avec les bonnes propriétés.
     */
    public static Alert getErreurJeu(String erreur) {
        ButtonType buttonFermer = new ButtonType("Fermer");
        Alert diag = new Alert(Alert.AlertType.WARNING, "ErreurDeJeu", buttonFermer);
        diag = setIcone(diag);
        VBox content = new VBox(new Label(erreur));
        content.setAlignment(Pos.CENTER);
        diag.setHeaderText("Erreur");
        diag.getDialogPane().setContent(content);
        diag.setTitle("Erreur");
        return diag;
    }

    /**
     * Alert qui dit que la sauvegarde est impossible.
     * @return Une alert avec les bonnes propriétés.
     */
    public static Alert getErreurSauvegarde() {
        ButtonType buttonFermer = new ButtonType("Fermer");
        Alert diag = new Alert(Alert.AlertType.WARNING, "ErreurSauvegarde", buttonFermer);
        diag = setIcone(diag);
        VBox content = new VBox(new Label("Vous ne pouvez pas sauvegarder pour le moment !"));
        content.setAlignment(Pos.CENTER);
        diag.setHeaderText("Erreur");
        diag.getDialogPane().setContent(content);
        diag.setTitle("Erreur");
        return diag;
    }

    /**
     * Alert qui dit que un ou plusieurs joueur ont pas de noms.
     * @return Une alert avec les bonnes propriétés.
     */
    public static Alert getErreurJoueurSansNom() {
        ButtonType buttonFermer = new ButtonType("Fermer");
        Alert diag = new Alert(Alert.AlertType.WARNING, "ErreurNom", buttonFermer);
        diag = setIcone(diag);
        VBox content = new VBox(new Label("Au moins un des joueurs n'a pas de nom, "),
                                new Label("vous ne pouvez donc pas lancer la partie !"));
        content.setAlignment(Pos.CENTER);
        diag.setHeaderText("Erreur");
        diag.getDialogPane().setContent(content);
        diag.setTitle("Erreur");
        return diag;
    }

    /**
     * Alert qui dit que le nom d'un joueur est trop long.
     * @return Une alert avec les bonnes propriétés.
     */
    public static Alert getErreurJoueurTropCaractere() {
        ButtonType buttonFermer = new ButtonType("Fermer");
        Alert diag = new Alert(Alert.AlertType.WARNING, "ErreurChar", buttonFermer);
        diag = setIcone(diag);
        VBox content = new VBox(new Label("Au moins un des joueurs possède un nom trop long, "),
                                new Label("vous ne pouvez donc pas lancer la partie !"));
        content.setAlignment(Pos.CENTER);
        diag.setHeaderText("Erreur");
        diag.getDialogPane().setContent(content);
        diag.setTitle("Erreur");
        return diag;
    }


    /**
     * Permet d'ajouter un icone à une Alert.
     * @param alert l'alert a modifier.
     * @return L'alert une fois modifiée.
     */
    public static Alert setIcone(Alert alert){
        final Image APPLICATION_ICON = new Image("file:assets/warning.png");
        Stage dialogStage = (Stage) alert.getDialogPane().getScene().getWindow();
        dialogStage.getIcons().add(APPLICATION_ICON);

        final ImageView DIALOG_HEADER_ICON = new ImageView("file:assets/warning.png");
        DIALOG_HEADER_ICON.setFitHeight(20);
        DIALOG_HEADER_ICON.setFitWidth(20);
        alert.getDialogPane().setGraphic(DIALOG_HEADER_ICON);
        return alert;
    }
}
