package model;

import java.io.Serializable;

/**
 * Represents non-functional description.
 * @author Group 6 - 1Y ICT A2020
 * @version 1.0 - December 2020 - December 2020
 * @since 1.0
 */
public class nonFunctionalDescription extends Description implements Serializable
{
    private String description;

    /**
     * One-argument constructor. Taking in a string, and setting the isFunctional value of Description class to false.
     * @param description A String containing the description.
     */
    public nonFunctionalDescription(String description)
    {
        super(false);
        this.description = description;
    }

    /**
     * Sets the non functional description.
     * @param description A String containing the description.
     */
    public void setNonFunctionalDescription(String description)
    {
        this.description = description;
    }

    /**
     * Gets the non functional description.
     * @return A String array containing one string with the non functional description.
     */
    @Override
    public String[] getDescription()
    {
        String[] output = new String[1];
        output[0] = description;
        return output;
    }

    /**
     * Gets the description in one string.
     * @return A string representing the description in one string.
     */
    public String toString()
    {
        return description;
    }
}
