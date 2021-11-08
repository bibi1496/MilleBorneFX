package Graphics.controlleur;

import Graphics.Javafx;
import Graphics.vue.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import Graphics.modele.mille_bornes.Jeu;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

/**
 * Gestion des évènements du menu.
 */
public class EcouteurMenu {
    private int nbDeJoueur;
    private Jeu jeuPrincipale;

    public EcouteurMenu(){}


    /**
     * Créer une sauvegarde si le bouton du menu détecte un changement.
     */
    public void onSauvegarde(ActionEvent actionEvent) {
        try{
            if (!this.sauvegarder())
                System.exit(0);
        }catch (IOException e){
            Erreurs.getErreurSauvegarde();
        }
    }

    /**
     * Affiche l'Alert à propos.
     */
    @FXML
    public void onAPropos(ActionEvent actionEvent) {
        APropos.getAPropos().showAndWait();
    }

    /**
     * Appel la méthode charger qui permet de charger une partie.
     * @param actionEvent
     */
    public void onCharger(ActionEvent actionEvent) {
        this.charger();
    }

    /**
     * Appel les Alert pour récupérer les informations nécessaires pour lancer une partie.
     * @param actionEvent
     */
    public void onNouvelle(ActionEvent actionEvent) {
        Jeu recuperationJeu;
        Optional<String> result = Alerts.getANouvelle().showAndWait();
        if(result.isPresent()){
            int num = Integer.parseInt(result.get());
            this.nbDeJoueur = num;
            recuperationJeu = Alerts.getAlertConfirmation(this.nbDeJoueur);
            if(recuperationJeu != null){
                Javafx.panneau.lancerLaPartie(recuperationJeu);
            }
        }
    }
    /**
     * Retourne un objet Jeu.
     * @return Retourne un objet jeu.
     */
    public Jeu getJeu() {
        return jeuPrincipale;
    }

    /** Charge une partie depuis le disque */
    public void charger() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir un dessin");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichiers texte", "*.json"),
                new FileChooser.ExtensionFilter("Tous fichiers", "*.*"));
        File fichier = fileChooser.showOpenDialog(null);
        if(fichier != null){
            if (!chargerFichier(fichier)) {
                Alert erreur = new Alert(Alert.AlertType.ERROR);
                erreur.setTitle("Ouverture d'une partie");
                erreur.setHeaderText(null);
                erreur.setContentText(String.format("Impossible d'ouvrir le fichier %s !", fichier.getAbsolutePath()));
                erreur.setResizable(true);
                erreur.showAndWait();
            }
            Javafx.panneau.lancerLaPartieDepuisSauvegarde(jeuPrincipale);
        }
    }
    /**
     *  Permet de récupérer l'objet Jeu depuis le fichier en entrée.
     */
    public boolean chargerFichier(File file) {
        try{
            JsonObject obj = JsonParser.parseReader(Files.newBufferedReader(file.toPath())).getAsJsonObject();
            this.jeuPrincipale = new Jeu(obj);
        }catch (IOException e){
            return false;
        }
        return true;
    }

    /**
     * Affiche une Alert pour sauvegarder une partie.
     * @throws IOException
     */
    public boolean sauvegarder() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer une partie");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichiers texte", "*.json"),
                new FileChooser.ExtensionFilter("Tous fichiers", "*.*"));
        File fichier = fileChooser.showSaveDialog(null);
        if(fichier != null){
            if(!ecrireDansFichier(fichier)){
                Alert erreur = new Alert(Alert.AlertType.ERROR);
                erreur.setTitle("Enregistrement de la partie");
                erreur.setHeaderText(null);
                erreur.setContentText(String.format("Impossible d'écrire le fichier %s !", fichier.getAbsolutePath()));
                erreur.setResizable(true);
                erreur.showAndWait();
            }
            return false;
        }
        return true;
    }

    /**
     * Permet d'écrire dans un fichier la sauvegarde de l'objet Jeu qui contient les informations d'une partie.
     * @param fichier
     * @return boolean pour savoir si l'opération a échouée ou non.
     */
    public boolean ecrireDansFichier(File fichier) {
        try{
            Files.writeString(fichier.toPath(), this.jeuPrincipale.sauvegarde().toString(), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        }catch (IOException e){
            return false;
        }
        return true;
    }
}



