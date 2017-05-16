/**
 * Class built to create and keep the Pokeball ImageView attributes.
 */
package project6;

import java.io.File;
import java.net.MalformedURLException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Name: Michael Rutkowski
 * Section: 13 
 * TA: Paul Quackenbush 
 * Instructor: Kathleen Timmerman
 */

public class Pokeball extends Trainer {

    private ImageView pokeballImageView;

    public Pokeball() throws MalformedURLException {
        File file = new File("resources/images/Poke_Ball_Sprite.png");
        Image image = new Image(file.toURI().toURL().toExternalForm());
        setPokeballImageView(new ImageView(image));
        pokeballImageView.setX(-500);
        pokeballImageView.setY(-500);

    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets an ImageView object from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The ImageView will be returned.
     *
     * @return pokeballImageView
     * *************************************************************************
     */
    public ImageView getPokeballImageView() {
        return pokeballImageView;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets an ImageView object.
     *
     * PRECONDITION: Needs a new ImageView as a parameter.
     *
     * POSTCONDITION: The pokeballImageView field will be set to the given
     * ImageView object.
     *
     * @param pokeballImageView
     * *************************************************************************
     */
    public void setPokeballImageView(ImageView pokeballImageView) {
        this.pokeballImageView = pokeballImageView;
    }

}
