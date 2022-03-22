package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Accumulates TeamMember class objects in an ArrayList teamMembers.
 * @author Group 6 - 1Y ICT A2020
 * @version 1.0 - December 2020
 * @since 1.0
 */
public class TeamMemberList implements Serializable
{

    private ArrayList<TeamMember> teamMembers;

    /**
     * Zero-argument constructor.
     * Creates the ArrayList of TeamMember class objects.
     */
    public TeamMemberList()
    {
        this.teamMembers = new ArrayList<TeamMember>();
    }

    /**
     * Creates a new Team Member in the arraylist
     * @param name A string containing the name.
     * @param email A string containing the email.
     */
    public void addTeamMember(String name, String email)
    {
        if (name.trim().equals(""))
            throw new IllegalArgumentException("Invalid name");
        else {

            for (int i = 0; i < teamMembers.size(); i++)
                if (teamMembers.get(i).getEmail().equals(email.trim()))
                    throw new IllegalArgumentException("The email is already used");

            teamMembers.add(new TeamMember(name, email));
        }
    }

    /**
     * Sets the email of a team member, if the email is not already used.
     * @param teamMember Contains the team member we want to change the email in.
     * @param email A String that contains the email.
     */
    public void setTeamMemberEmail(TeamMember teamMember, String email)
    {
        for (int i = 0; i < teamMembers.size(); i++)
            if(teamMembers.get(i).getEmail().equals(email.trim()))
                throw new IllegalArgumentException("The email is already used");


        teamMembers.get(teamMembers.indexOf(teamMember)).setEmail(email);
    }

    /**
     * Deletes a team member from list of members.
     * @param teamMember Contains the team member we want to remove.
     */
    public void deleteTeamMember(TeamMember teamMember)
    {
        for (TeamMember teamMember1 : this.teamMembers)
            if (teamMember1.getName().equals(teamMember.getName())
                    && teamMember1.getEmail().equals(teamMember.getEmail()))
            {
                this.teamMembers.remove(teamMember1);
                break;
            }
    }

    /**
     * Gets all team members.
     * @return An array representing all the team members that were created.
     */
    public TeamMember[] getAllTeamMembers()
    {
        return teamMembers.toArray(new TeamMember[0]);
    }
}
