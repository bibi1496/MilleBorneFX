package Graphics.modele.mille_bornes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import Graphics.modele.mille_bornes.joueurs.Gentil;
import Graphics.modele.mille_bornes.joueurs.Humain;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Lance le jeu de Mille-Bornes avec un ensemble de joueurs.
 */
public class Main {
  public static void main(String[] args) {
    Jeu jeu;
    if (args.length == 0) {
      Joueur j1 = new Humain("moi");
      Joueur j2 = new Humain("toi");
      jeu = new Jeu(j1, j2);
      jeu.ajouteJoueurs(new Gentil("lui"));
      jeu.ajouteJoueurs(new Gentil("nous"));
    } else {
      try {
        JsonObject obj = JsonParser.parseReader(Files.newBufferedReader(Path.of("save.json"))).getAsJsonObject();
        jeu = new Jeu(obj);
      } catch (IOException e) {
        e.printStackTrace();
        System.exit(-1);
        return;
      }
    }
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      if (! jeu.estPartieFinie()) try {
        Files.writeString(Path.of("save.json"), jeu.sauvegarde().toString(), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        System.err.println("Partie sauvegardée !");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }));
    while (!jeu.joue()) {
      System.out.println();
    }
    System.out.printf("Le gagnant est : %s%n", jeu.getGagnant().toString());
  }
}
