/**
 * Class built to contain the Trainer ImageView and its attributes.
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

public class Trainer extends HudSpecifics {

    private ImageView trainerImageView;

    public Trainer() throws MalformedURLException {

        File file = new File("resources/images/trainerthrow1.png");
        Image image = new Image(file.toURI().toURL().toExternalForm());
        setTrainerImageView(new ImageView(image));
        trainerImageView.setX(getScreenWidth() - ((getScreenWidth() / 2)));
        trainerImageView.setY(getScreenHeight() - 126);
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets an ImageView object from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The ImageView will be returned.
     *
     * @return trainerImageView
     * *************************************************************************
     */
    public ImageView getTrainerImageView() {
        return trainerImageView;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets an ImageView object.
     *
     * PRECONDITION: Needs a new ImageView as a parameter.
     *
     * POSTCONDITION: The trainerImageView field will be set to the given
     * ImageView object.
     *
     * @param trainerImageView
     * *************************************************************************
     */
    public void setTrainerImageView(ImageView trainerImageView) {
        this.trainerImageView = trainerImageView;
    }

}
