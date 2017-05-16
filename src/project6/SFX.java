/**
 * Class built to store the available sound effects and music in the game.
 */
package project6;

import java.io.File;
import java.net.MalformedURLException;
import javafx.scene.media.AudioClip;

/**
 * Name: Michael Rutkowski
 * Section: 13 
 * TA: Paul Quackenbush 
 * Instructor: Kathleen Timmerman
 */

public class SFX {

    private AudioClip mainTheme;
    private AudioClip captureSFX;
    private AudioClip throwSound;
    private AudioClip mySong;

    public SFX() throws MalformedURLException {
        
        File mainThemeResource = new File("resources/audio/Safari Zone - Pokémon Omega Ruby and Alpha Sapphire Music (Fanmade).wav");
        setMainTheme(new AudioClip(mainThemeResource.toURI().toURL().toExternalForm()));
        
        File captureSFXResource = new File("resources/audio/pokeball sound.wav");
        setCaptureSFX(new AudioClip(captureSFXResource.toURI().toURL().toExternalForm()));
        
        File throwSoundResource = new File("resources/audio/throw sound.wav");
        setThrowSound(new AudioClip(throwSoundResource.toURI().toURL().toExternalForm()));
        
        File mySong = new File("resources/audio/Kuba Oms - My love (Lyric).wav");
        setMySong(new AudioClip(mySong.toURI().toURL().toExternalForm()));
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets an AudioClip object from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The AudioClip will be returned.
     *
     * @return mainTheme
     * *************************************************************************
     */
    public AudioClip getMainTheme() {
        return mainTheme;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets an AudioClip.
     *
     * PRECONDITION: Needs a new AudioClip object as a parameter.
     *
     * POSTCONDITION: The mainTheme field will be set to the given AudioClip
     * object.
     *
     * @param mainTheme
     * *************************************************************************
     */
    public void setMainTheme(AudioClip mainTheme) {
        this.mainTheme = mainTheme;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets an AudioClip object from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The AudioClip will be returned.
     *
     * @return captureSFX
     * *************************************************************************
     */
    public AudioClip getCaptureSFX() {
        return captureSFX;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets an AudioClip.
     *
     * PRECONDITION: Needs a new AudioClip object as a parameter.
     *
     * POSTCONDITION: The captureSFX field will be set to the given AudioClip
     * object.
     *
     * @param captureSFX
     * *************************************************************************
     */
    public void setCaptureSFX(AudioClip captureSFX) {
        this.captureSFX = captureSFX;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets an AudioClip object from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The AudioClip will be returned.
     *
     * @return throwSound
     * *************************************************************************
     */
    public AudioClip getThrowSound() {
        return throwSound;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets an AudioClip.
     *
     * PRECONDITION: Needs a new AudioClip object as a parameter.
     *
     * POSTCONDITION: The throwSound field will be set to the given AudioClip
     * object.
     *
     * @param throwSound
     * *************************************************************************
     */
    public void setThrowSound(AudioClip throwSound) {
        this.throwSound = throwSound;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets an AudioClip object from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The AudioClip will be returned.
     *
     * @return mySong
     * *************************************************************************
     */
    public AudioClip getMySong() {
        return mySong;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets an AudioClip.
     *
     * PRECONDITION: Needs a new AudioClip object as a parameter.
     *
     * POSTCONDITION: The mySong field will be set to the given AudioClip
     * object.
     *
     * @param mySong
     * *************************************************************************
     */
    public void setMySong(AudioClip mySong) {
        this.mySong = mySong;
    }
}
