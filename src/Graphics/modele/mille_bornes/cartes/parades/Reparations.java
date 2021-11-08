package Graphics.modele.mille_bornes.cartes.parades;

import Graphics.modele.mille_bornes.cartes.Attaque;
import Graphics.modele.mille_bornes.cartes.Parade;

/**
 * Décrit la carte Réparations. (6x dans le jeu)
 * Elle contre les Accidents
 */
public class Reparations extends Parade {
  public Reparations() {
    super("Reparations");
  }

  @Override
  public boolean contre(Attaque carte) {
    return carte.estContreeParReparations();
  }

}
