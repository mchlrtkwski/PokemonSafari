/**
 * This Class provides the main method and primary stage methods.
 */
package project6;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Name: Michael Rutkowski
 * Section: 13 
 * TA: Paul Quackenbush 
 * Instructor: Kathleen Timmerman
 */

public class Project6 extends Application {

    @Override
    public void start(Stage primaryStage) throws MalformedURLException {
        
        //Creates Array and Array List
        ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();
        int[] pokemonScoreArray = createPokemonScoreArray();
        fillPokemonArrayList(pokemonList);

        //Sets up gamePane
        GamePane gamePane = new GamePane(pokemonList, pokemonScoreArray, primaryStage);
        
        //Gives the instuctions for the game with large images
        StackPane instructionScreen = new StackPane();
        instructionScreen.getChildren().add(new ImageView(new Image(new File("resources/images/instructions.png").toURI().toURL().toExternalForm())));
           
         //Set main Pane.
        Pane root = new Pane();
        root.getChildren().addAll(gamePane, instructionScreen);
        
        //Sets up key input.
        gamePane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT) {
                if (gamePane.getTrainer().getTrainerImageView().getX() < gamePane.getSize().getScreenWidth() - 160) {
                    gamePane.moveRight();
                }
            } else if (e.getCode() == KeyCode.LEFT) {
                if (gamePane.getTrainer().getTrainerImageView().getX() > 0) {
                    gamePane.moveLeft();
                }
            } else if (e.getCode() == KeyCode.SPACE) {
                if (gamePane.getChildren().contains(gamePane.getPokeball().getPokeballImageView())) {
                } else {
                    try {
                        gamePane.firePokeball();
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(Project6.class.getName()).log(Level.SEVERE, null, ex);
                    }//remove instructions
                    if (root.getChildren().contains(instructionScreen)){
                        root.getChildren().remove(instructionScreen);
                    }
                }
                //Sound Test trial song.   
            } else if (e.getCode() == KeyCode.W) {
                if (!gamePane.getSfx().getMySong().isPlaying()) {
                    if (gamePane.getSfx().getMainTheme().isPlaying()) {
                        gamePane.getSfx().getMainTheme().stop();
                    }
                    gamePane.getSfx().getMySong().play();
                }
            }
        }
        );

        //Set Pane to Scene.
        Scene scene = new Scene(root, 1000, 1000);
        //Set Scene to Stage.
        primaryStage.setTitle("The Safari Zone");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);

        primaryStage.show();
        gamePane.requestFocus();
    }

    /**
     * @param pokemonList
     * *************************************************************************
     * DESCRIPTION: This method fills an Array list with all 151 Pokemon in a
     * random order.
     *
     * PRECONDITION: In order for this method to function properly. It must be
     * given a <Pokemon> ArrayList as a parameter.
     *
     * POSTCONDITION: After the method has ran, the ArrayList given will be
     * filled with random Pokemon objects.
     *
     * @return pokemonList
     *
     * @throws java.net.MalformedURLException
     * *************************************************************************
     */
    public static ArrayList fillPokemonArrayList(ArrayList<Pokemon> pokemonList) throws MalformedURLException {
        for (int i = 1; i < 152; i++) {
            pokemonList.add(new Pokemon(Pokemon.createIndex(pokemonList)));
        }
        return pokemonList;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method creates a Array with the scores of the pokemon's
     * game value. This list was deliberately made to match the rarity of the
     * pokemon.
     *
     * PRECONDITION: One must simply call this method in order for it to run
     * properly.
     *
     * POSTCONDITION: This Array will be returned to the main method.
     *
     * @return pokemonScoreArray
     * *************************************************************************
     */
    public static int[] createPokemonScoreArray() {
        int[] pokemonScoreArray = new int[]{
            2, 4, 7, 2, 4, 8, 2, 4, 7, 1, 2, 3, 1, 2, 3, 2, 4, 6, 2, 5, 2,
            4, 3, 5, 4, 6, 4, 5, 2, 4, 6, 2, 4, 7, 5, 6, 4, 7, 3, 5, 1, 4,
            1, 3, 7, 2, 4, 3, 5, 5, 6, 4, 5, 2, 6, 3, 6, 4, 8, 2, 5, 7, 1,
            5, 8, 4, 5, 8, 1, 3, 6, 1, 5, 2, 4, 6, 5, 6, 3, 6, 4, 6, 6, 2,
            6, 4, 6, 3, 5, 4, 6, 2, 5, 8, 7, 4, 6, 3, 6, 4, 6, 2, 7, 4, 6,
            8, 8, 6, 4, 6, 6, 7, 8, 7, 8, 2, 7, 2, 6, 3, 6, 7, 8, 8, 8, 8,
            8, 8, 1, 9, 9, 4, 4, 6, 6, 6, 8, 6, 7, 6, 7, 8, 7, 9, 9, 9, 4,
            7, 10, 10, 10};
        return pokemonScoreArray;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
