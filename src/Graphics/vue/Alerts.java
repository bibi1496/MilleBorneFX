package Graphics.vue;

import Graphics.modele.mille_bornes.cartes.Attaque;
import Graphics.modele.mille_bornes.cartes.Carte;
import Graphics.modele.mille_bornes.cartes.attaques.LimiteVitesse;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Graphics.modele.mille_bornes.Jeu;
import Graphics.modele.mille_bornes.Joueur;
import Graphics.modele.mille_bornes.joueurs.Gentil;
import Graphics.modele.mille_bornes.joueurs.Humain;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Alerts {

    /**
     * Alert qui permet de récupérer le type de joueur et leurs noms.
     * @param nbJoueur indique le nombre de joueur pour cette partie.
     * @return Le jeu initialisé.
     */
    public static Jeu getAlertConfirmation(int nbJoueur) {
        Alert nomJoueur = new Alert(Alert.AlertType.NONE, "Nom Joueur", ButtonType.CANCEL, ButtonType.FINISH);
        nomJoueur.setTitle("Nouvelle partie");
        nomJoueur = setIcone(nomJoueur,"file:assets/logoMilleBorne.png");
        List<String> choices = new ArrayList<>();
        choices.add("Humain");
        choices.add("IA");
        VBox vbox = new VBox();
        ArrayList<ChoiceBox>  box = new ArrayList<>();
        ArrayList<TextField>  boxText = new ArrayList<>();

        for (int i = 0 ; i<nbJoueur;i++){
            HBox hbox = new HBox();
            ChoiceBox<String> dialog = new ChoiceBox(FXCollections.observableArrayList("IA", "Humain"));
            dialog.setValue("Humain");
            box.add(dialog);
            boxText.add(new TextField());
            hbox.getChildren().addAll(box.get(i), boxText.get(i));

            vbox.getChildren().add(hbox);
        }
        nomJoueur.getDialogPane().setContent(vbox);

        ButtonType boutonOk = new ButtonType("Ok");
        ButtonType boutonAnnuler = new ButtonType("Annuler");

        nomJoueur.getButtonTypes().clear();
        nomJoueur.getButtonTypes().add(boutonOk);
        nomJoueur.getButtonTypes().add(boutonAnnuler);

        Optional<ButtonType> option = nomJoueur.showAndWait();

        if (option.get() == boutonAnnuler){
            return null;
        }


        //Erreurs.getErreurJoueurSansNom();
        // modif pour que si annule on annule la création de jeu

        Joueur[] joueur = new Joueur[nbJoueur];
        for (int i=0; i< nbJoueur;i++){
            switch ((String) box.get(i).getValue()){
                case "IA":
                    if(boxText.get(i).getText().equals("")){
                        Erreurs.getErreurJoueurSansNom().showAndWait();
                        return null;
                    }else if (boxText.get(i).getText().length() > 6){
                        Erreurs.getErreurJoueurTropCaractere().showAndWait();
                        return null;
                    }
                    joueur[i] = new Gentil(boxText.get(i).getText());
                break;
                default:
                    if(boxText.get(i).getText().equals("")){
                        Erreurs.getErreurJoueurSansNom().showAndWait();
                        return null;
                    }else if (boxText.get(i).getText().length() > 6){
                        Erreurs.getErreurJoueurTropCaractere().showAndWait();
                        return null;
                    }
                    joueur[i] = new Humain(boxText.get(i).getText());
                break;
            }
        }
        Jeu jeu = new Jeu(joueur);
        return jeu;
    }

    /**
     * Permet de récupérer le nombre de joueur pour cette partie.
     * @return Une alert avec les bonnes propriétés.
     */
    public static ChoiceDialog<String> getANouvelle(){
        List<String> choices = new ArrayList<>();
        choices.add("2");
        choices.add("3");
        choices.add("4");
        ChoiceDialog<String> dialog = new ChoiceDialog<>("2", choices);
        dialog.setHeaderText("Combien de joueur ?");
        dialog.setContentText("Combien de joueur ?");
        dialog.setTitle("Nouvelle partie");
        return dialog;
    }

    /**
     * Alert qui affiche le gagnant avec des informations supplémentaires
     * et ferme l'application quand elle est fermé.
     * @param jeu principale.
     * @param zone qui a gagné.
     */
    public static void getAlertPartieFini(Jeu jeu, ZonePrincipale zone) {
        Alert diag = new Alert(Alert.AlertType.CONFIRMATION);
        diag = setIcone(diag,"file:assets/trophee.png");
        VBox content = new VBox(new Label("Les gagnants :"));
        for (Joueur joueur : jeu.getGagnant()){
            content.getChildren().add(new Label(joueur.toString()));
        }
        content.setAlignment(Pos.CENTER);
        diag.setHeaderText("La partie est fini !");
        diag.getDialogPane().setContent(content);
        diag.setTitle("Partie terminé");

        ButtonType bouton = new ButtonType("Quitter");
        diag.getButtonTypes().clear();
        diag.getButtonTypes().add(bouton);

        diag.showAndWait();

        System.exit(0);
    }

    /**
     * Alert qui demande au joueur courent qui attaquer quand il possède une
     * carte Attaque (automatique quand il y a que deux joueurs dans la partie).
     * @param jeu principale.
     * @param carte
     * @return joueur à attaquer.
     */
    public static Joueur getNouveauAdversaire(Jeu jeu, Carte carte) {

        // Si il y a deux joueurs dans la partie
        if (jeu.getJoueurs().size() == 2) {
            return jeu.getJoueurActif().getProchainJoueur();
        }

        // Pour le nombre de joueurs > 2
        Joueur joueurResult = null;
        VBox lesBoutonVBox = new VBox();
        ToggleGroup group = new ToggleGroup();
        List<RadioButton> lesBoutonsRadios = new ArrayList<>();

        // Récupérer tous les joueurs
        List<Joueur> joueurs = new ArrayList<>();
        Joueur leJoueur = jeu.getJoueurActif().getProchainJoueur();
        joueurs.add(leJoueur);
        while (!leJoueur.getProchainJoueur().nom.equals(jeu.getJoueurActif().nom)) {
            leJoueur = leJoueur.getProchainJoueur();
            joueurs.add(leJoueur);
        }

        // ajouter un bouton pour chaque joueurs
        for (Joueur joueur : joueurs){
            if (carte instanceof LimiteVitesse && !joueur.getLimiteVitesse()){
                RadioButton button = new RadioButton(joueur.nom);
                button.setToggleGroup(group);
                lesBoutonsRadios.add(button);
                lesBoutonVBox.getChildren().add(button);
            }
            if (!(joueur.getBataille() instanceof Attaque) && joueur.getBataille() != null){
                RadioButton button = new RadioButton(joueur.nom);
                button.setToggleGroup(group);
                lesBoutonsRadios.add(button);
                lesBoutonVBox.getChildren().add(button);
           }
        }

        System.out.println(lesBoutonsRadios.size());

        if (lesBoutonsRadios.size() == 0 ) {
            Erreurs.getErreurJeu("Tous le monde est déjà attaqué !");
            return null;
        } else if(lesBoutonsRadios.get(0) != null) {
            lesBoutonsRadios.get(0).setSelected(true);
        }

        // Alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert = setIcone(alert,"file:assets/logoMilleBorne.png");
        alert.setTitle("Choix d'un adversaire :");
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(lesBoutonVBox);

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            RadioButton button = (RadioButton) group.getSelectedToggle();

            for (Joueur joueur : joueurs){
                if(button.getText() == joueur.nom){
                    joueurResult = joueur;
                }
            }
        } else if (option.get() == ButtonType.CANCEL) {
            return null;
        }
        return joueurResult;
    }

    /**
     * Permet d'ajouter un icone à une Alert.
     * @param alert L'alert à modifier.
     * @param path Le chemin de l'image.
     * @return L'alert une fois modifiée.
     */
    public static Alert setIcone(Alert alert, String path){
        final Image APPLICATION_ICON = new Image(path);
        Stage dialogStage = (Stage) alert.getDialogPane().getScene().getWindow();
        dialogStage.getIcons().add(APPLICATION_ICON);
        return alert;
    }
}