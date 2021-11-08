package Graphics.vue;

import javafx.scene.image.ImageView;

/**
 * Gestion des images.
 */
public class ImageCarte {
    /**
     * Modifie les propriétés d'une image.
     * @param image à modifier.
     * @return l'Image avec de nouvelles propriétés.
     */
    public static ImageView setFont(ImageView image){
        image.setPreserveRatio(true);
        image.setFitHeight(85);
        image.setFitWidth(85);

        return image;
    }
}
