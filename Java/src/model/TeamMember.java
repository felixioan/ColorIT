package model;

import java.io.Serializable;

/**
 * Represents a team member.
 * Has a name and a unique email which is stored in an Email class.
 * @author Group 6 - 1Y ICT A2020
 * @version 1.0 - December 2020 - December 2020
 * @since 1.0
 */
public class TeamMember implements Serializable
{
    private String name;
    private Email email;

    /**
     * Two-argument constructor. Creates a team member.
     * @param name A String containing the name, trimmed of any spaces.
     * @param email A String containing the email, trimmed of any spaces.
     */
    public TeamMember(String name, String email)
    {
        this.name = name.trim();
        this.email = new Email(email.trim());
    }

    /**
     * Gets the name.
     * @return A string representing the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the email.
     * @return A string representing the email.
     */
    public String getEmail()
    {
        return email.getEmail();
    }

    /**
     * Sets the name, if its valid.
     * @param name A string containing the name, trimmed of any spaces
     */
    public void setName(String name)
    {
        if (name.trim().equals(""))
            throw new IllegalArgumentException("Invalid name");
        else
        {
            this.name = name.trim();
        }
    }

    /**
     * Sets the email by calling .setEmail.
     * @param email A string containing the email, trimmed of any spaces.
     */
    public void setEmail(String email)
    {
        this.email.setEmail(email.trim());
    }

    /**
     * Returns the answer of comparison of two objects, whether the two are equal.
     * @param teamMember Contains the team member we want to compare to.
     * @return True if the objects are the same. False if they are different.
     */
    public boolean equals(TeamMember teamMember)
    {
        if(!(teamMember instanceof TeamMember))
            return false;

        return this.name.equals(teamMember.getName()) &&
                this.email.getEmail().equals(teamMember.getEmail());
    }

    public String toString()
    {
        return name + ", " + email;
    }

}
