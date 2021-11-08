package Graphics.modele.mille_bornes.cartes.bottes;

import Graphics.modele.mille_bornes.EtatJoueur;
import Graphics.modele.mille_bornes.Jeu;
import Graphics.modele.mille_bornes.cartes.Attaque;
import Graphics.modele.mille_bornes.cartes.Bataille;
import Graphics.modele.mille_bornes.cartes.Botte;

/**
 * Décrit la carte As du Volant. (Botte, unique dans le jeu)
 * Elle contre les Accidents
 */
public final class AsDuVolant extends Botte {
  /** La seule instance du jeu */
  public static final AsDuVolant unique = new AsDuVolant();

  private AsDuVolant() {
    super("AsDuVolant");
  }

  public boolean contre(Attaque carte) {
    return carte.estContreeParAsDuVolant();
  }

  @Override
  public void appliqueEffet(Jeu jeu, EtatJoueur joueur) {
    Bataille bataille = joueur.getBataille();
    if (bataille != null && bataille.estContreeParAsDuVolant())
      joueur.defausseBataille(jeu);
    joueur.addBotte(this);
    jeu.setProchainJoueur(jeu.getJoueurActif());
  }
}
