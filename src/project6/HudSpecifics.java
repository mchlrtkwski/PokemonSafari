/**
 * A class built for finding the screen size. Method Help provided by Paul
 * Quackenbush
 */
package project6;

/**
 * Name: Michael Rutkowski
 * Section: 13 
 * TA: Paul Quackenbush 
 * Instructor: Kathleen Timmerman
 */

public class HudSpecifics {

    private double screenHeight;
    private double screenWidth;

    public HudSpecifics() {
        setScreenHeight(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
        setScreenWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets a double value from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The double value will be returned.
     *
     * @return
     * *************************************************************************
     */
    public double getScreenHeight() {
        return screenHeight;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets a double value.
     *
     * PRECONDITION: Needs a double value object as a parameter.
     *
     * POSTCONDITION: The screenHeight field will be set to the given double
     * value.
     *
     * @param screenHeight
     * *************************************************************************
     */
    public void setScreenHeight(double screenHeight) {
        this.screenHeight = screenHeight;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method gets a double value from the field.
     *
     * PRECONDITION: No parameters needed.
     *
     * POSTCONDITION: The double value will be returned.
     *
     * @return screenWidth
     * *************************************************************************
     */
    public double getScreenWidth() {
        return screenWidth;
    }

    /**
     * *************************************************************************
     * DESCRIPTION: This method sets a double value.
     *
     * PRECONDITION: Needs a double value object as a parameter.
     *
     * POSTCONDITION: The screenWidth field will be set to the given double
     * value.
     *
     * @param screenWidth
     * *************************************************************************
     */
    public void setScreenWidth(double screenWidth) {
        this.screenWidth = screenWidth;
    }
}
