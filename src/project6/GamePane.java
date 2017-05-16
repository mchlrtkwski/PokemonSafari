/**
 * Provides Main Game Screen and thus most game operation methods run through
 * here.
 */
package project6;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Name: Michael Rutkowski 
 * Section: 13 
 * TA: Paul Quackenbush 
 * Instructor: Kathleen Timmerman
 */
public class GamePane extends Pane {

    private Timeline movementControls;
    private Timeline throwPokeball;
    private Trainer trainer;
    private SFX sfx;
    private Pokeball pokeball;
    private int index;
    private ScreenDisplay screenDisplay;
    private int score;
    private TextField scoreBox;
    private HudSpecifics size;
    private int missedShots;

    public GamePane(ArrayList<Pokemon> pokemonArrayList, int[] pokemonScoreArray, Stage primaryStage) throws MalformedURLException {
        
        //Sets the fields.
        setSize(size = new HudSpecifics());
        setMissedShots(missedShots = 0);
        setTrainer(trainer = new Trainer());
        setScore(0);
        setPokeball(pokeball = new Pokeball());
        setIndex(0);
        setScreenDisplay(screenDisplay = new ScreenDisplay());
        setSfx(sfx = new SFX());
        setScoreBox(scoreBox = new TextField());
        scoreBox.setText("" + getScore());
        scoreBox.setEditable(false);

        //Adds the button.
        Button btn = new Button();
        btn.setText("Throw PokeBall!");
        btn.setPrefSize(125, 50);
        btn.setLayoutX(size.getScreenWidth() - 125);
        btn.setLayoutY(size.getScreenHeight() - 50);
        btn.setOnAction((javafx.event.ActionEvent e) -> {
            if (!getChildren().contains(getPokeball().getPokeballImageView())) {
                try {
                    firePokeball();
                } catch (MalformedURLException ex) {
                    Logger.getLogger(GamePane.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //plays the main them. Loops 3 times.
        sfx.getMainTheme().setCycleCount(3);
        sfx.getMainTheme().play();
        
        //New ArrayList.
        ArrayList<Pokemon> spriteSummonList = new ArrayList<Pokemon>();
        
        //Adds nodes to Pane
        getChildren().addAll(
                screenDisplay.getBackground(),
                btn,
                scoreBox,
                trainer.getTrainerImageView());
        
        //Starts the targets.
        createSpriteTimer(pokemonArrayList, spriteSummonList);
        
        //Starts collistion detection
        setMovementControls(new Timeline(new KeyFrame(Duration.millis(50),
                (e) -> detectCollisions(spriteSummonList,
                        pokemonArrayList,
                        pokemonScoreArray, primaryStage))));
        getMovementControls().setCycleCount(Timeline.INDEFINITE);
        getMovementControls().play();

        //Sets up the throwPokeball animation.
        setThrowPokeball(new Timeline(new KeyFrame(Duration.millis(50),
                (e) -> throwPokeball())));
        getThrowPokeball().setCycleCount(Timeline.INDEFINITE);

    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets a Timeline animation from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The Timeline animation will be returned.
     *
     * @return movementControls
     * *************************************************************************
     */
    public Timeline getMovementControls() {
        return movementControls;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets a Timeline animation.
     *
     * PRECONDITION: Needs a new Timeline as a parameter.
     *
     * POSTCONDITION: the movementControls field will be set to the given
     * Timeline animation.
     *
     * @param movementControls
     * *************************************************************************
     */
    public void setMovementControls(Timeline movementControls) {
        this.movementControls = movementControls;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets a Timeline animation from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The Timeline animation will be returned.
     *
     * @return throwPokeball
     * *************************************************************************
     */
    public Timeline getThrowPokeball() {
        return throwPokeball;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets a Timeline animation.
     *
     * PRECONDITION: Needs a new Timeline as a parameter.
     *
     * POSTCONDITION: The throwPokeball field will be set to the given Timeline
     * animation
     *
     * @param throwPokeball
     * ************************************************************************
     */
    public void setThrowPokeball(Timeline throwPokeball) {
        this.throwPokeball = throwPokeball;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets a Trainer object from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The Trainer object will be returned.
     *
     * @return trainer
     * *************************************************************************
     */
    public Trainer getTrainer() {
        return trainer;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets a Trainer object.
     *
     * PRECONDITION: Needs a new Trainer object as a parameter.
     *
     * POSTCONDITION: The trainer field will be set to the given Trainer object.
     *
     * @param trainer
     * *************************************************************************
     */
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets a SFX object from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The SFX object will be returned.
     *
     * @return sfx
     * *************************************************************************
     */
    public SFX getSfx() {
        return sfx;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets a SFX object.
     *
     * PRECONDITION: Needs a SFX object as a parameter.
     *
     * POSTCONDITION: The sfx field will be set to the given SFX object.
     *
     * @param sfx
     * *************************************************************************
     */
    public void setSfx(SFX sfx) {
        this.sfx = sfx;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets a Pokeball object from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The Pokeball object will be returned.
     *
     * @return pokeball
     * *************************************************************************
     */
    public Pokeball getPokeball() {
        return pokeball;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets a Pokeball object.
     *
     * PRECONDITION: Needs a Pokeball object as a parameter.
     *
     * POSTCONDITION: The pokeball field will be set to the given pokeball
     * object.
     *
     * @param pokeball
     * *************************************************************************
     *
     */
    public void setPokeball(Pokeball pokeball) {
        this.pokeball = pokeball;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets a integer value from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The integer value will be returned.
     *
     * @return index
     * *************************************************************************
     */
    public int getIndex() {
        return index;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets an integer value.
     *
     * PRECONDITION: Needs an integer value as a parameter.
     *
     * POSTCONDITION: The index field will be set to the given integer value.
     *
     * @param index
     * *************************************************************************
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets a ScreenDisplay object from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The ScreenDisplay object will be returned.
     *
     * @return screenDisplay
     * *************************************************************************
     */
    public ScreenDisplay getScreenDisplay() {
        return screenDisplay;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets a ScreenDisplay object.
     *
     * PRECONDITION: Needs a ScreenDisplay object as a parameter.
     *
     * POSTCONDITION:
     *
     * @param screenDisplay
     * *************************************************************************
     */
    public void setScreenDisplay(ScreenDisplay screenDisplay) {
        this.screenDisplay = screenDisplay;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets an integer value from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The integer value will be returned.
     *
     * @return score
     * *************************************************************************
     */
    public int getScore() {
        return score;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets an integer value.
     *
     * PRECONDITION: Needs an integer value as a parameter.
     *
     * POSTCONDITION: The score field will be set to the given integer value.
     *
     * @param score
     * *************************************************************************
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets a TextField object from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The TextField object will be returned.
     *
     * @return scoreBox
     * *************************************************************************
     */
    public TextField getScoreBox() {
        return scoreBox;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets a TextField object.
     *
     * PRECONDITION: Needs a TextField object as a parameter.
     *
     * POSTCONDITION: The scoreBox field will be set to the given Textfield
     * object.
     *
     * @param scoreBox
     * *************************************************************************
     */
    public void setScoreBox(TextField scoreBox) {
        this.scoreBox = scoreBox;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets a HudSpecific object from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The HudSpecific object will be returned.
     *
     * @return size
     * *************************************************************************
     */
    public HudSpecifics getSize() {
        return size;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets a HudSpecifc object.
     *
     * PRECONDITION: Needs a HudSpecific object as a parameter.
     *
     * POSTCONDITION: The size field will be set to the given HudSpecific
     * object.
     *
     * @param size
     * *************************************************************************
     */
    public void setSize(HudSpecifics size) {
        this.size = size;
    }

    /**
     * *****************************************************************************
     * DESCRIPTION: This method gets a integer value from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The integer value will be returned.
     *
     * @return missedShots
     * *************************************************************************
     */
    public int getMissedShots() {
        return missedShots;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets an integer value.
     *
     * PRECONDITION: Needs an integer value as a parameter.
     *
     * POSTCONDITION: The missedShots field will be set to the given integer
     * value.
     *
     * @param missedShots
     * *************************************************************************
     */
    public void setMissedShots(int missedShots) {
        this.missedShots = missedShots;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method moves the trainer sprite image to the right of
     * the screen.
     *
     * PRECONDITION: In order for this method to run properly, it must be called
     * upon after the trainer field has been set.
     *
     * POSTCONDITION: The X coordinate will be increased. and the sprite will
     * shift to the right.
     *
     * *************************************************************************
     */
    public void moveRight() {
        trainer.getTrainerImageView().setX( //Adds the quotiant of the screen  width divided by 20.
                trainer.getTrainerImageView().getX() + size.getScreenWidth() / 20);
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method moves the trainer sprite image to the left of
     * the screen.
     *
     * PRECONDITION: In order for this method to run properly, it must be called
     * upon after the trainer field has been set.
     *
     * POSTCONDITION: The X coordinate will be decreased. and the sprite will
     * shift to the left.
     *
     * *************************************************************************
     */
    public void moveLeft() {
        trainer.getTrainerImageView().setX( //Subtracts the quotiant of the screen  width divided by 20.
                trainer.getTrainerImageView().getX() - size.getScreenWidth() / 20);
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method "fires" a pokeball image from the sprite.
     *
     * PRECONDITION: In order for this method to properly. All fields must be
     * created, and the user must simply press the SPACE bar to activate.
     *
     * POSTCONDITION: After this method is ran, the pokeballImageView will be
     * added to the Pane, the coordinates of the ball will be changed to be that
     * of the trainer the throwPokeball animation will be invoked. An image of
     * the sprite during "mid-throw" will be passed to the throwBallAnimation
     * and the sfx field calls a method for a sound effect.
     *
     * @throws java.net.MalformedURLException
     * *************************************************************************
     */
    public void firePokeball() throws MalformedURLException {

        //Sets up new coodinates for the ImageView and adds it to the Pane.
        pokeball.getPokeballImageView().setX(
                trainer.getTrainerImageView().getX() + 80);
        pokeball.getPokeballImageView().setY(
                trainer.getTrainerImageView().getY() + 80);
        getChildren().add(pokeball.getPokeballImageView());

        //Plays animation.
        getThrowPokeball().playFromStart();

        //Sets up and displays an Image ofthe Sprite in action.
        File file = new File("resources/images/trainerthrow3.png");
        Image image = new Image(file.toURI().toURL().toExternalForm());
        ImageView midThrowTrainerImage = new ImageView(image);
        midThrowTrainerImage.setX(trainer.getTrainerImageView().getX());
        midThrowTrainerImage.setY(trainer.getTrainerImageView().getY());
        getChildren().add(midThrowTrainerImage);

        //Invokes animation to remove the Sprite action image
        throwBallAnimation(midThrowTrainerImage);

        //Plays sound effect.
        sfx.getThrowSound().play();

    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method allow runs constantly in order to detect ball to
     * pokemon collisions, pokemon to bounds collisions, and ball to bounds
     * collisions.
     *
     * PRECONDITION: in order for this method to run properly, this method must
     * be given the spriteSummonList, the pokemonArrayList and the
     * pokemonScoreArray. The pokemonArrayList is the original randomly created
     * list in the start of the program. The spriteSummonList is a list that
     * filters what pokemon are on screen and which are not as to avoid lagging
     * issues from checking for all collisions constantly. The pokemonScoreArray
     * helps in order to determine the score for the "caught" pokemon.
     *
     * POSTCONDITION: Upon running this method, the program will constantly
     * check and see if the pokemon in the spriteSummonList, are detecting
     * collisions. If the ball does hit a pokemon on screen, the pokemon is
     * removed from list and the index of the pokemon is checked in the
     * scoreArray and added to the total score. If the pokemon hits the bounds
     * of the screen, they are removed from the spriteSummonList. If the
     * pokeball hits the screen bounds then the missed shots counter increases
     * to 1
     *
     * @param spriteSummonList
     * @param pokemonArrayList
     * @param pokemonScoreArray
     * *************************************************************************
     */
    public void detectCollisions(ArrayList<Pokemon> spriteSummonList, ArrayList<Pokemon> pokemonArrayList, int[] pokemonScoreArray, Stage primaryStage) {

        //Checks every Pokemon object part of spriteSummonList.
        for (int i = 0; i < spriteSummonList.size(); i++) {

            //If the pokeball and the pokemon intersect, then stop the animation
            //of the pokemon, move the pokeball corrdinates far off screen,
            //remove the pokeball from the pane, invoke the capturePokemon
            //method/animation, play the sound effect, set the score and remove
            //the pokemon from the spriteSummonList
            if (pokeball.getPokeballImageView().getLayoutBounds().intersects(
                    spriteSummonList.get(i).getPokemonImageView().getLayoutBounds())) {

                spriteSummonList.get(i).getPokemonPathTransition().stop();
                pokeball.getPokeballImageView().setX(-500);
                pokeball.getPokeballImageView().setX(-500);
                getChildren().remove(pokeball.getPokeballImageView());
                capturePokemon(spriteSummonList, i);
                getChildren().remove(spriteSummonList.get(i).getPokemonImageView());
                sfx.getCaptureSFX().play();
                setScore(getScore() + pokemonScoreArray[spriteSummonList.get(i).getPokemonIndex() - 1]);
                scoreBox.setText("" + getScore());
                spriteSummonList.remove(i);
                
                if (getScore() >= 100){
                     getChildren().removeAll(getChildren());
                     StackPane stackpane = new StackPane();
                     TextField textfield= new TextField();
                     textfield.setText("YOU WIN!");
                     stackpane.getChildren().add(textfield);
                     getChildren().add(stackpane);
                     primaryStage.close();
                     
                     
                    }

                //If the pokemon sprite hits the boundaries of the screen remove
                //it from the spriteSummonList.
            } else if ((spriteSummonList.get(i).getPokemonImageView().getLayoutBounds().intersects(-300, 0, 0, size.getScreenHeight()))
                    || (spriteSummonList.get(i).getPokemonImageView().getLayoutBounds().intersects(size.getScreenWidth() + 300, 0, 0, size.getScreenHeight()))) {

                getChildren().remove(spriteSummonList.get(i).getPokemonImageView());
                spriteSummonList.remove(i);
            }
            //As soon as the pokeball hits intersectst the top bounds, move the 
            //pokeball off screen. This is done to avoid multiple intersections 
            //over the animation period. One intersects adds one to the 
            //missedShots field.
            if (pokeball.getPokeballImageView().getLayoutBounds().intersects(0, 0, size.getScreenWidth(), 0)) {

                pokeball.getPokeballImageView().setX(-500);
                pokeball.getPokeballImageView().setX(-500);
                getChildren().remove(pokeball.getPokeballImageView());
                setMissedShots(getMissedShots() + 1);
                
                if (getMissedShots() == 10){
                     getChildren().removeAll(getChildren());
                     StackPane stackpane = new StackPane();
                     TextField textfield= new TextField();
                     textfield.setText("YOU LOSE");
                     stackpane.getChildren().add(textfield);
                     getChildren().add(stackpane);
                     primaryStage.close();
                    }
            }
        }
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method decreases the y coordinate of the pokeball
     * imageView.
     *
     * PRECONDITION: In order for this method to run properly per its purpose,
     * it must be called upon with a TimeLine animation.
     *
     * POSTCONDITION: The pokeball will move upward on the screen.
     *
     * *************************************************************************
     */
    public void throwPokeball() {

        pokeball.getPokeballImageView().setY(//number of pixels moved is kept in ration to the screen height.
                pokeball.getPokeballImageView().getY() - (size.getScreenHeight() / 50));

    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method creates and plays an animation of removing the
     * "mid-action" sprite image from the pane.
     *
     * PRECONDITION: The "mid-action" sprite image must already exist on the
     * screen. .
     *
     * POSTCONDITION: After the method is ran the the "mid-action" sprite image
     * will be removed from the pane.
     *
     * @param imageView1
     * *************************************************************************
     */
    public void throwBallAnimation(ImageView imageView1) {
        Animation throwBallAnimation = new Timeline(new KeyFrame(Duration.millis(1), (e) -> {
            getChildren().remove(imageView1);
        }));
        throwBallAnimation.setCycleCount(1);
        throwBallAnimation.setDelay(Duration.millis(100));
        throwBallAnimation.play();

    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method creates and plays an animation when called upon.
     *
     * PRECONDITION: The method requires that it calls the startTimer() method.
     *
     * POSTCONDITION: After the method is ran the startTimer() method will run
     * for the desired length of time.
     *
     * @param pokemonArrayList
     * @param spriteSummonList
     * *************************************************************************
     */
    public void createSpriteTimer(ArrayList<Pokemon> pokemonArrayList, ArrayList<Pokemon> spriteSummonList) {

        //Creates new Timeline animation to run the startTimer() method.
        Animation spriteTimer = new Timeline(new KeyFrame(Duration.seconds(2),
                (e) -> startTimer(pokemonArrayList, spriteSummonList)));
        spriteTimer.setCycleCount(151);
        spriteTimer.play();
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method displays the moving targets across the screen.
     *
     * PRECONDITION: In order for this method to run properly, it must be given
     * the pokemonArrayList and spriteSummonList.
     *
     * POSTCONDITION: After this method runs, a Timeline animation will run and
     * pokemon will start being displayed to the screen.
     *
     * @param pokemonArrayList
     * @param spriteSummonList
     * *************************************************************************
     */
    public void startTimer(ArrayList<Pokemon> pokemonArrayList, ArrayList<Pokemon> spriteSummonList) {

        //This method runs through a count of the field index. it then adds to 
        //Pane, the pokemon at the desired spot in the pokemonArrayList. The the 
        //animation of the pokemon plays and the pokemon is added to the
        //spriteSummonList so that it is detected for collision.
        getChildren().add(pokemonArrayList.get(getIndex()).getPokemonImageView());
        pokemonArrayList.get(getIndex()).getPokemonPathTransition().play();
        spriteSummonList.add(pokemonArrayList.get(getIndex()));
        setIndex(getIndex() + 1);
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method plays an animation of capturing the pokemon, in
     * order to let the user recognize they have received a "hit".
     *
     * PRECONDITION: In order for this method to run properly it must be given
     * the spriteSummonList and an integer. When used in context to the program,
     * the integer referenced is the current integer looping when the collision
     * detector hits. It does this in order to pull the last known coordinates
     * of the pokemonImage and place the animation at that spot.
     *
     * POSTCONDITION: An animation of a red implosion will display to the screen
     * and shrink until it is no longer visible.
     *
     * @param spriteSummonList
     * @param i
     * *************************************************************************
     */
    public void capturePokemon(ArrayList<Pokemon> spriteSummonList, int i) {

        //Obtains image of implosion.
        File file = new File("resources/images/250px-Shout_Pink.png");
        Image image = null;
        try {
            image = new Image(file.toURI().toURL().toExternalForm());
        } catch (MalformedURLException ex) {
            Logger.getLogger(GamePane.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageView redImplosion = new ImageView(image);
        redImplosion.setX(spriteSummonList.get(i).getPokemonImageView().getX() - (spriteSummonList.get(i).getPokemonImageView().getFitWidth() / 4));
        redImplosion.setY(spriteSummonList.get(i).getPokemonImageView().getY() - (spriteSummonList.get(i).getPokemonImageView().getFitHeight() / 4));
        getChildren().add(redImplosion);

        //Creates and plays a shrinking ScaleTransition.
        ScaleTransition shrinkNode = new ScaleTransition(Duration.millis(500), redImplosion);
        shrinkNode.setToX(0);
        shrinkNode.setToY(0);
        shrinkNode.setCycleCount(1);
        shrinkNode.play();
    }

}
