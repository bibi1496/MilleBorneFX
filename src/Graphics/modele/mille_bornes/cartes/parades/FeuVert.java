package Graphics.modele.mille_bornes.cartes.parades;

import Graphics.modele.mille_bornes.EtatJoueur;
import Graphics.modele.mille_bornes.Jeu;
import Graphics.modele.mille_bornes.cartes.Attaque;
import Graphics.modele.mille_bornes.cartes.Parade;

/**
 * Décrit la carte Feu Vert. (14x dans le jeu)
 * Elle contre les Feux Rouges et est nécessaire pour démarrer
 */
public class FeuVert extends Parade {
  public FeuVert() {
    super("FeuVert");
  }

  @Override
  public boolean contre(Attaque carte) {
    return carte.estContreeParFeuVert();
  }

  @Override
  public void appliqueEffet(Jeu jeu, EtatJoueur joueur) throws IllegalStateException {
    if (joueur.getBataille() == null)
      joueur.setBataille(this);
    else
      super.appliqueEffet(jeu, joueur);
  }

}
