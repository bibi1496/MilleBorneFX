package Graphics.modele.mille_bornes.cartes.attaques;

import Graphics.modele.mille_bornes.cartes.Attaque;

/**
 * Décrit la carte Accident (3x dans le jeu)
 * Elle est contrée par l'As du Volant (botte) et par les Réparations
 */
public class Accident extends Attaque {

  public Accident() {
    super("Accident");
  }

  @Override
  public boolean estContreeParAsDuVolant() {
    return true;
  }

  @Override
  public boolean estContreeParReparations() {
    return true;
  }

}
