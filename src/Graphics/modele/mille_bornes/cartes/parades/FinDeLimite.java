package Graphics.modele.mille_bornes.cartes.parades;

import Graphics.modele.mille_bornes.EtatJoueur;
import Graphics.modele.mille_bornes.Jeu;
import Graphics.modele.mille_bornes.cartes.Attaque;
import Graphics.modele.mille_bornes.cartes.Parade;

/**
 * Décrit la carte Fin de Limite de Vitesse. (6x dans le jeu)
 * Elle contre les Limites de Vitesse
 */
public class FinDeLimite extends Parade {
  public FinDeLimite() {
    super("FinDeLimite");
  }

  @Override
  public boolean contre(Attaque carte) {
    return carte.estContreeParFinDeLimite();
  }

  @Override
  public void appliqueEffet(Jeu jeu, EtatJoueur joueur) throws IllegalStateException {
    if (!joueur.getLimiteVitesse()) throw new IllegalStateException("La vitesse n'est pas limitée !");
    joueur.setLimiteVitesse(false);
  }

}
