package Graphics.vue;

import Graphics.modele.mille_bornes.Jeu;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Classe qui englobe le contenue du visuel de l'application. (Centralise le contrôle de l'application)
 */
public class PanneauJeu {

    /**
     * Stock la zone principale ou on retrouve le jeu.
     */
    private ZonePrincipale zonePrincipale = new ZonePrincipale();

    /**
     * Fenêtre principale de l'application.
     */
    private VBox root;

    /**
     * Zone principale contenant tous le jeu.
     */
    private BorderPane border;

    /**
     * Constructeur.
     */
    public PanneauJeu(){
    }

    /**
     * Charge le FXML et ajoute le borderPane initialisé dans une vbox.
     * @throws IOException
     * @return une Vbox avec tous les éléments nécessaire pour jouer (MenuBar, ZonePrincipale).
     */
    public VBox lancer() throws IOException {
        this.root = FXMLLoader.load(getClass().getResource("main.fxml"));

        border = this.zonePrincipale.defautPlateau();
        border.setStyle("-fx-background-color: rgb(91,255,148)");
        this.border.minWidthProperty().bind(root.widthProperty());
        MenuBar menuBar =(MenuBar) root.getChildren().get(0);
        this.border.minHeightProperty().bind(root.heightProperty().subtract(menuBar.heightProperty()));
        this.root.getChildren().add(this.border);
        return root;
    }

    /**
     * Initialise la vue et le modèle depuis un nouvelle objet Jeu.
     * @param jeu utilisé pour le jeu.
     */
    public void lancerLaPartieDepuisSauvegarde(Jeu jeu){
        zonePrincipale.clearVisuel();
        zonePrincipale.setJeuPrincipale(jeu);
        zonePrincipale.initialiserNbDeJoueur(jeu.getJoueurs().size(),border);
        zonePrincipale.initialiserLeJeuSauvegarde(jeu);
    }

    /**
     * Initialise la vue et le modèle.
     * @param jeu principale.
     */
    public void lancerLaPartie(Jeu jeu) {
        zonePrincipale.clearVisuel();
        zonePrincipale.setJeuPrincipale(jeu);
        zonePrincipale.initialiserNbDeJoueur(jeu.getJoueurs().size(),border);
        zonePrincipale.initialiserLeJeu(jeu);
    }
}
