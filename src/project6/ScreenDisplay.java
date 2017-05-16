/**
 * Class built to store displays for the scene.
 */
package project6;

import java.io.File;
import java.net.MalformedURLException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Name: Michael Rutkowski
 * Section: 13 
 * TA: Paul Quackenbush 
 * Instructor: Kathleen Timmerman
 */

public class ScreenDisplay extends HudSpecifics {

    private StackPane background;

    public ScreenDisplay() throws MalformedURLException {
       
        File file1 = new File("resources/images/safarizone.png");
        Image image1 = new Image(file1.toURI().toURL().toExternalForm());
        ImageView imageView1 = new ImageView(image1);
        StackPane stackPane1 = new StackPane();
        stackPane1.getChildren().add(imageView1);
        setBackground(stackPane1);

    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets an StackPane object from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The StackPane will be returned.
     *
     * @return background
     * *************************************************************************
     */
    public StackPane getBackground() {
        return background;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets a StackPane.
     *
     * PRECONDITION: Needs a new StackPane object as a parameter.
     *
     * POSTCONDITION: The background field will be set to the given AudioClip
     * object.
     *
     * @param background
     * *************************************************************************
     */
    public void setBackground(StackPane background) {
        this.background = background;
    }

}
