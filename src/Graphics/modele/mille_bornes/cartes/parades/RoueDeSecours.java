package Graphics.modele.mille_bornes.cartes.parades;

import Graphics.modele.mille_bornes.cartes.Attaque;
import Graphics.modele.mille_bornes.cartes.Parade;

/**
 * Décrit la carte Roue de Secours. (6x dans le jeu)
 * Elle contre les Crevaisons
 */
public class RoueDeSecours extends Parade {
  public RoueDeSecours() {
    super("RoueDeSecours");
  }

  @Override
  public boolean contre(Attaque carte) {
    return carte.estContreeParRoueDeSecours();
  }

}
