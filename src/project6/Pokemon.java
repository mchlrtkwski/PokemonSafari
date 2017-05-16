/**
 * Class built for the Pokemon ImageView attibutes and create animation paths.
 */
package project6;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Name: Michael Rutkowski
 * Section: 13 
 * TA: Paul Quackenbush 
 * Instructor: Kathleen Timmerman
 */

public class Pokemon extends HudSpecifics {

    private int pokemonIndex;
    private ImageView pokemonImageView;
    private Animation pokemonPathTransition;

    Pokemon(int pokemonIndex) throws MalformedURLException {
        setPokemonIndex(pokemonIndex);
        File pokemonResource = new File("resources/images/pokemon/" + pokemonIndex + ".gif");
        Image pokemonImage = new Image(pokemonResource.toURI().toURL().toExternalForm());
        ImageView pokemonImageView = new ImageView(pokemonImage);
        setPokemonImageView(pokemonImageView);
        setPokemonPathTransition(createTarget());

    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method is used to generate 151 unique numbers at random
     * between 1 and 151. It then returns these numbers and Pokemon objects are
     * made with the integers.
     *
     * PRECONDITION: In order for this method to run properly, this method needs
     * the pokemonArrayList.
     *
     * POSTCONDITION: After this method runs, it produces random numbers between
     * 1 and 151 and checks to see if that index number has already been used in
     * the pokemonArrayList
     *
     * @param pokemonList
     * *************************************************************************
     */
    public static int createIndex(ArrayList<Pokemon> pokemonList) {

        boolean isValid = false;
        //Index starts at -1 to loop the ArrayList
        int index = -1;
        //If empty, generate any number
        if (pokemonList.isEmpty()) {
            index = (int) (Math.random() * 151 + 1);
            return index;
            //if not empty...
        } else {
            while (isValid == false) {
                index = (int) (Math.random() * 151 + 1);
                //Checks the index of every pokemon in the ArrayList
                for (int i = 0; i < pokemonList.size(); i++) {
                    //If that index already exists, break out of and re-enter While loop
                    if (index == pokemonList.get(i).getPokemonIndex()) {
                        isValid = false;
                        break;
                        //If not leave the while loop
                    } else {
                        isValid = true;
                    }

                }
            }
        }
        return index;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets a integer value from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The integer value will be returned.
     *
     * @return pokemonIndex
     * *************************************************************************
     */
    public int getPokemonIndex() {
        return pokemonIndex;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets an integer value.
     *
     * PRECONDITION: Needs a new integer value as a parameter.
     *
     * POSTCONDITION: The pokemonIndex field will be set to the given integer
     * value.
     *
     * @param pokemonIndex
     * *************************************************************************
     */
    public void setPokemonIndex(int pokemonIndex) {
        this.pokemonIndex = pokemonIndex;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets an ImageView object from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The ImageView will be returned.
     *
     * @return pokemonImageView
     * *************************************************************************
     */
    public ImageView getPokemonImageView() {
        return pokemonImageView;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets an ImageView object.
     *
     * PRECONDITION: Needs a new ImageView as a parameter.
     *
     * POSTCONDITION: The pokemonImageView field will be set to the given
     * ImageView object.
     *
     * @param pokemonImageView
     * *************************************************************************
     */
    public void setPokemonImageView(ImageView pokemonImageView) {
        this.pokemonImageView = pokemonImageView;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets a Trainer object from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The Trainer object will be returned.
     *
     * @return pokemonPathTransition
     * *************************************************************************
     */
    public Animation getPokemonPathTransition() {
        return pokemonPathTransition;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets a Timeline animation.
     *
     * PRECONDITION: Needs a new Timeline as a parameter.
     *
     * POSTCONDITION: The pokemonPathTransition field will be set to the given
     * Timeline animation
     *
     * @param pokemonPathTransition
     * *************************************************************************
     */
    public void setPokemonPathTransition(Animation pokemonPathTransition) {
        this.pokemonPathTransition = pokemonPathTransition;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method generates a path for the Pokemon to traverse.
     *
     * PRECONDITION: In order for this method to work properly, it should be
     * called in the constructor of a Pokemon object.
     *
     * POSTCONDITION: An animation will be returned.
     *
     * @return moveToRight || moveToLeft
     * *************************************************************************
     */
    public Animation createTarget() {

        int randomPath = (int) (Math.random() * 2) + 1;
        //Sets up animation path where pokemon moves to the right.
        if (randomPath == 1) {
            this.getPokemonImageView().setX(-250);
            this.getPokemonImageView().setY((int) (Math.random() * (getScreenHeight() - 300)) + 1);
            Animation moveToRight = new Transition() {
                {
                    this.setCycleCount(4000);
                    this.setCycleDuration(Duration.seconds(5));
                }

                @Override
                protected void interpolate(double frac) {
                    getPokemonImageView().setX(getPokemonImageView().getX() + getScreenWidth() / 500);
                }
            };
            return moveToRight;

            //Sets up animation path where pokemon moves to the left.
        } else {
            this.getPokemonImageView().setX(getScreenWidth() + 250);
            this.getPokemonImageView().setY((int) (Math.random() * (getScreenHeight() - 300)) + 1);
            Animation moveToLeft = new Transition() {
                {
                    this.setCycleCount(4000);
                    this.setCycleDuration(Duration.seconds(5));
                }

                @Override
                protected void interpolate(double frac) {
                    getPokemonImageView().setX(getPokemonImageView().getX() - getScreenWidth() / 500);
                }
            };
            return moveToLeft;
        }
    }

}
