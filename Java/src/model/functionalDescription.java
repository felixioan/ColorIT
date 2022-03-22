package model;

import java.io.Serializable;

/**
 * Represents functional description.
 * @author Group 6 - 1Y ICT A2020
 * @version 1.0 - December 2020 - December 2020
 * @since 1.0
 */
public class functionalDescription extends Description implements Serializable {

    private String who;
    private String what;
    private String why;

    /**
     * One-argument constructor. Taking in an array of (three) strings, and setting the isFunctional value of Description class to true.
     * @param description A String array containing the description, in the Who, What, Why pattern.
     */
    public functionalDescription(String[] description)
    {
        super(true);
        this.who = description[0];
        this.what = description[1];
        this.why = description[2];
    }

    /**
     * Sets the Functional Description.
     * @param who A string containing the answer to who?
     * @param what A string containing the answer to what?
     * @param why A string containing the answer to why?
     */
    public void setFunctionalDescription(String who, String what, String why)
    {
        this.who = who;
        this.what = what;
        this.why = why;
    }

    /**
     * Gets the description.
     * @return A string array representing the functional description.
     */
    @Override
    public String[] getDescription()
    {
        String[] output = new String[3];

        output[0] = who;
        output[1] = what;
        output[2] = why;

        return output;
    }

    /**
     * Gets the full description in one string.
     * @return The description in the Who, What, Why pattern.
     */
    public String toString()
    {
        return "who: " + who +
                ", what: " + what +
                ", why: " + why;
    }
}
