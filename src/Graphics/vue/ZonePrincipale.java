package Graphics.vue;

import Graphics.modele.mille_bornes.Jeu;
import Graphics.modele.mille_bornes.cartes.Categorie;
import Graphics.modele.mille_bornes.cartes.attaques.LimiteVitesse;
import Graphics.modele.mille_bornes.cartes.parades.FinDeLimite;
import Graphics.modele.mille_bornes.joueurs.Gentil;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import Graphics.modele.mille_bornes.Joueur;
import Graphics.modele.mille_bornes.cartes.Carte;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de gérer le board du jeu (affichage)
 */
public class ZonePrincipale {

    /**
     * Zone principale contenant tous le jeu.
     */
    private BorderPane zone;

    /**
     * Zone du sabot.
     */
    private HBox milieu;

    /**
     * Zone contenant le joueur du haut.
     */
    private GridPane paneTop;

    /**
     * Zone contenant le joueur de gauche.
     */
    private GridPane paneLeft;

    /**
     * Zone contenant le joueur de droite.
     */
    private GridPane paneRight;

    /**
     * Zone du joueur courent.
     */
    private GridPane paneBottom;

    /**
     * VBox contenant un label "pioche", une image de carte retournée
     * et le nombre de cartes restantes dans le jeu.
     */
    private VBox pioche;

    /**
     * VBox contenant un label "défausse" et une image de carte.
     */
    private VBox defausse;

    /**
     * Liste des zones utiliser pour le jeu.
     */
    private ArrayList<GridPane> listPane;

    /**
     * Stock le jeu courent.
     */
    private Jeu jeuPrincipale;

    /**
     * Savoir si la partie viens d'une sauvegarde.
     */
    private boolean estSave = false;

    /**
     * Constructeur.
     */
    public ZonePrincipale(){ }

    /**
     * Réinitialise la vue.
     */
    public void clearVisuel(){
        zone.getChildren().clear();
    }

    /**
     * Initialise le plateau.
     * @return La zone principale.
     */
    public BorderPane defautPlateau(){
        this.zone = new BorderPane();
        setMilieuZone(new ImageView("file:assets/cartes/Vide.jpg"));
        return zone;
    }

    /**
     * Actualise le sabot du jeu.
     * @param defausse L'image à afficher sur la défausse.
     */
    public void setMilieuZone(ImageView defausse){
        this.milieu = new HBox();
        this.pioche = new VBox(new Label("Pioche"));
        this.defausse = new VBox(new Label("Defausse"));
        VBox nbCartes = null;
        if (jeuPrincipale != null){
            nbCartes = new VBox(new Label(jeuPrincipale.getNbCartesSabot()+" Cartes"));
        }

        // image Defausse
        ImageView iconeDefausse = defausse;
        iconeDefausse.setPreserveRatio(true);
        iconeDefausse.setFitHeight(100);
        iconeDefausse.setFitWidth(100);

        // image Pioche
        ImageView iconePioche = new ImageView("file:assets/cartes/Null.jpg");
        iconePioche.setPreserveRatio(true);
        iconePioche.setFitHeight(100);
        iconePioche.setFitWidth(100);

        this.pioche.setAlignment(Pos.CENTER);
        this.defausse.setAlignment(Pos.CENTER);
        this.milieu.setAlignment(Pos.CENTER);
        this.milieu.getChildren().addAll(this.pioche,this.defausse);
        this.pioche.getChildren().add(iconePioche);
        this.defausse.getChildren().add(iconeDefausse);

        if (nbCartes != null)
            this.pioche.getChildren().add(nbCartes);
        else
            this.pioche.getChildren().add(new VBox(new Label("93 Cartes")));
        this.defausse.getChildren().add(new VBox(new Label(" ")));

        zone.setCenter(this.milieu);
    }

    public void setJeuPrincipale(Jeu jeuPrincipale) {
        this.jeuPrincipale = jeuPrincipale;
    }

    /**
     * Initialise les gridsPane pour l'affichage en fonction des joueurs dans la partie.
     * @param nbDeJoueur le nombre de joueurs dans la partie (2, 3 ou 4).
     * @param jeuDebut Le borderPane contenant la fenêtre de jeu.
     */
    public void initialiserNbDeJoueur(int nbDeJoueur, BorderPane jeuDebut){
        this.listPane = new ArrayList<>();
        this.paneBottom = new GridPane();
        paneBottom.setStyle("-fx-padding: 10px");
        if(nbDeJoueur == 2){
            paneTop = new GridPane();
            listPane.add(paneBottom);
            listPane.add(paneTop);
            ZonePrincipale.appliqueStyle12(paneTop);
            ZonePrincipale.appliqueStyle12(paneBottom);
            jeuDebut.setTop(paneTop);
            jeuDebut.setBottom(paneBottom);
            paneTop.setAlignment(Pos.CENTER);
            paneBottom.setAlignment(Pos.CENTER);
        }else if(nbDeJoueur == 3){
            paneLeft = new GridPane();
            paneRight = new GridPane();
            listPane.add(paneBottom);
            listPane.add(paneRight);
            listPane.add(paneLeft);
            ZonePrincipale.appliqueStyle12(paneBottom);
            ZonePrincipale.appliqueStyle34(paneLeft);
            ZonePrincipale.appliqueStyle34(paneRight);
            jeuDebut.setBottom(paneBottom);
            jeuDebut.setLeft(paneLeft);
            jeuDebut.setRight(paneRight);
            paneBottom.setAlignment(Pos.CENTER);
            paneLeft.setAlignment(Pos.CENTER);
            paneRight.setAlignment(Pos.CENTER);
        }else if(nbDeJoueur == 4) {
            paneTop = new GridPane();
            paneLeft = new GridPane();
            paneRight = new GridPane();
            listPane.add(paneBottom);
            listPane.add(paneTop);
            listPane.add(paneLeft);
            listPane.add(paneRight);
            ZonePrincipale.appliqueStyle12(paneTop);
            ZonePrincipale.appliqueStyle12(paneBottom);
            ZonePrincipale.appliqueStyle34(paneRight);
            ZonePrincipale.appliqueStyle34(paneLeft);
            jeuDebut.setTop(paneTop);
            jeuDebut.setBottom(paneBottom);
            jeuDebut.setRight(paneRight);
            jeuDebut.setLeft(paneLeft);
            paneTop.setAlignment(Pos.CENTER);
            paneLeft.setAlignment(Pos.CENTER);
            paneRight.setAlignment(Pos.CENTER);
            paneBottom.setAlignment(Pos.CENTER);
        }
    }

    /**
     * Prepare le jeu dans le modèle et prépare le jeu dans la vue.
     * @param jeu Principale
     */
    public void initialiserLeJeu(Jeu jeu){
        this.jeuPrincipale = jeu;
        jeu.prepareJeu();
        actualiserJeu(jeu);
    }

    /**
     * Récupère l'objet jeu depuis la sauvegarde et actualise la vue en fonction du nouvelle objet.
     * @param jeu
     */
    public void initialiserLeJeuSauvegarde(Jeu jeu){
        this.jeuPrincipale = jeu;
        this.estSave = true;
        actualiserJeu(jeu);
    }

    /**
     * Actualise la vue grâce au modèle.
     * @param jeu Jeu principale
     */
    public void actualiserJeu(Jeu jeu) {
        if (jeuPrincipale.regardeDefausse() != null){
            setMilieuZone(Carte.typeCarteAffichage(jeuPrincipale.regardeDefausse()));
        }else {
            setMilieuZone(new ImageView("file:assets/cartes/Vide.jpg"));
        }


        if (jeuPrincipale.estPartieFinie()) {
            Alerts.getAlertPartieFini(jeu, this);
        }
        if (!estSave) {
            jeuPrincipale.activeProchainJoueurEtTireCarte();
        }else {
            estSave = false;
        }

        // actualiser les GridPanes
        for (GridPane pane : this.listPane) {
            pane.getChildren().clear();
        }

        // récupérer tous les joueurs du jeu dans la variable joueurs
        List<Joueur> joueurs = new ArrayList<>();
        joueurs.add(jeu.getJoueurActif());
        Joueur leJoueur = jeu.getJoueurActif().getProchainJoueur();
        joueurs.add(leJoueur);
        while (!leJoueur.getProchainJoueur().nom.equals(jeu.getJoueurActif().nom)) {
            leJoueur = leJoueur.getProchainJoueur();
            joueurs.add(leJoueur);
        }

        // Visuels
        int numeroCarte = 1;
        for (int i = 0; i < joueurs.size(); i++) {
            if (i == 0) {
                for (Carte carte : jeu.getJoueurActif().getMain()) {
                    setCarteJoueurActifCarte(carte, numeroCarte);
                    setVisuelJoueurActif(joueurs.get(i));
                    numeroCarte++;
                }
            } else {
                setVisuelJoueurNonActif(joueurs.get(i), i);
            }
        }

        if (jeu.getJoueurActif() instanceof Gentil) {
            Gentil ia = (Gentil) jeu.getJoueurActif();
            try{
                int carte = ia.choisitCarte();
                if (carte > 0) {
                    jeuPrincipale.getJoueurActif().joueCarte(jeuPrincipale, carte-1);
                } else {
                    jeuPrincipale.getJoueurActif().defausseCarte(jeuPrincipale, -carte-1);
                }
                actualiserJeu(jeuPrincipale);
            }catch (IllegalArgumentException e){}
        }
    }

    /**
     * Affiche la carte et ajoute un Event dessus pour détecter les cliques de souris.
     * @param carte à afficher
     * @param numero pour savoir ou l'afficher dans le Gridpane.
     */
    public void setCarteJoueurActifCarte(Carte carte, int numero){
        ImageView image = Carte.typeCarteAffichage(carte);
        image.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                ZonePrincipale.this.gestionEventJoueCarte(carte, numero);
            } else if (event.getButton() == MouseButton.SECONDARY){
                ZonePrincipale.this.gestionEventDefausseCarte(carte, numero);
            }
        });
        this.listPane.get(0).add(image,numero,2);
    }

    /**
     * Joue la carte est actualise la vue.
     * @param carte à jouer.
     * @param numero de la carte à jouer.
     */
    private void gestionEventJoueCarte(Carte carte, int numero) {
        if(carte.categorie == Categorie.Attaque){
            try{
                Joueur adversaire = Alerts.getNouveauAdversaire(jeuPrincipale, carte);
                if(adversaire != null){
                    jeuPrincipale.getJoueurActif().joueCarte(jeuPrincipale, numero-1, adversaire);
                    actualiserJeu(jeuPrincipale);
                }else {
                    Erreurs.getErreurJeu("Pas de joueur ciblé à attaqué !");
                }
            }catch (IllegalStateException e){
                Erreurs.getErreurJeu(e.getMessage()).show();
            }
        }else {
            try{
                jeuPrincipale.getJoueurActif().joueCarte(jeuPrincipale, numero-1);
                actualiserJeu(jeuPrincipale);
            }catch (IllegalStateException e){
                Erreurs.getErreurJeu(e.getMessage()).show();
            }
        }
    }

    /**
     * Défausse une carte et actualise la vue.
     * @param carte à défausser.
     * @param numero de la carte à défausser.
     */
    private void gestionEventDefausseCarte(Carte carte, int numero) {
        jeuPrincipale.getJoueurActif().defausseCarte(jeuPrincipale,numero-1);
        actualiserJeu(jeuPrincipale);
    }

    /**
     * Permet de récupérer un joueur et d'afficher ses cartes et des informations pour les afficher en bas.
     * @param joueur à récupérer pour mettre à jour le visuel.
     */
    public void setVisuelJoueurActif(Joueur joueur){
        int ValeurPourBotte = 4;
        Label labelNom = this.setCssLabel(new Label(joueur.nom));
        Label labelKm = this.setCssLabel(new Label(joueur.getKm()+" km"));
        this.listPane.get(0).add(labelKm, 3,1);
        this.listPane.get(0).add(labelNom, 4,0);
        if(!joueur.getLimiteVitesse()){
            this.listPane.get(0).add(Carte.typeCarteAffichage(new FinDeLimite()),1,1);
        }
        else{
            this.listPane.get(0).add(Carte.typeCarteAffichage(new LimiteVitesse()),1,1);
        }
        if(joueur.getBataille() ==null){
            ImageView imageAfficher = new ImageView("file:assets/cartes/Vide.jpg");
            imageAfficher = ImageCarte.setFont(imageAfficher);
            this.listPane.get(0).add(imageAfficher,2,1);
        }else{
            this.listPane.get(0).add(Carte.typeCarteAffichage(joueur.getBataille()), 2, 1);
        }
        for(Carte i:joueur.getBottes()){
            this.listPane.get(0).add(Carte.typeCarteAffichage(i), ValeurPourBotte, 1);
            ValeurPourBotte += 1;
        }
    }

    /**
     * Applique à un Label du css.
     * @param label à modifier.
     * @return label modifié.
     */
    public Label setCssLabel(Label label){
        label.setStyle("-fx-font-size: 20px ; -fx-font-weight: bold");
        return label;
    }

    /**
     * Permet d'actualiser le visuel d'un joueur qui n'est pas actif.
     * @param joueur pour récupérer les informations.
     * @param numero à utiliser pour la position.
     */
    public void setVisuelJoueurNonActif(Joueur joueur, int numero){
        GridPane recuperationGrid = this.listPane.get(numero);
        Label labelNom =  this.setCssLabel(new Label(joueur.nom));
        Label labelKm = this.setCssLabel(new Label(joueur.getKm()+" km"));
        recuperationGrid.add(labelNom, 2,0);
        recuperationGrid.add(labelKm, 3,1);
        if(!joueur.getLimiteVitesse()){
            recuperationGrid.add(Carte.typeCarteAffichage(new FinDeLimite()),1,1);
        }
        else{
            recuperationGrid.add(Carte.typeCarteAffichage(new LimiteVitesse()),1,1);
        }
        if(joueur.getBataille()==null){
            ImageView imageAfficher = new ImageView("file:assets/cartes/Vide.jpg");
            imageAfficher = ImageCarte.setFont(imageAfficher);
            recuperationGrid.add(imageAfficher, 2, 1);
        }else{
            recuperationGrid.add(Carte.typeCarteAffichage(joueur.getBataille()), 2, 1);
        }
        int ValeurPourBotte = 1;
        for(Carte i:joueur.getBottes()){
            recuperationGrid.add(Carte.typeCarteAffichage(i),ValeurPourBotte,3);
            ValeurPourBotte += 1;
        }
    }

    /**
     * Modifie les propriétés des pane en haut et en bas.
     * @param pane à modifier.
     */
    private static void appliqueStyle12(Pane pane){
        pane.setMinHeight(200);
        pane.setMaxHeight(200);
        pane.setStyle("-fx-background-color: rgb(91, 255, 116);");
    }

    /**
     * Modifie les propriétés des pane à gauche et à droite.
     * @param pane à modifier.
     */
    private static void appliqueStyle34(Pane pane){
        pane.setMinWidth(200);
        pane.setMaxWidth(200);
        pane.setStyle("-fx-background-color: rgb(91, 255, 116);");
    }
}








