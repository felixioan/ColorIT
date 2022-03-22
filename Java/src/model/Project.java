package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a project.
 * @author Group 6 - 1Y ICT A2020
 * @version 1.0 - December 2020
 * @since 1.0
 */
public class Project implements Serializable
{
    private String name;
    private int projectID;
    private static int idCounter = 0;
    /**
     * Represents a description of the project, chosen by the user.
     */
    private String description;
    private TeamMember scrumMaster;
    private TeamMember productOwner;
    /**
     * Represents the people(team members) involved in the project.
     */
    private ArrayList<TeamMember> projectTeam;
    /**
     * Represents the list of requirements that are part of the project, stored in a RequirementList class.
     */
    private RequirementList requirementList;


    /**
     * Creates a Project with the specified name and the description.
     * The project is also assigned an ID based on the static idCounter.
     * Project has automatically created a projectTeam,
     * and a requirementList that holds the requirements for this project
     * @param name The name of the project.
     * @param description The description of the project.
     */
    public Project(String name, String description) 
    {
        idCounter++;
        this.name = name;
        this.description = description;
        this.projectID = idCounter;
        this.projectTeam = new ArrayList<TeamMember>();
        this.requirementList = new RequirementList();

    }

    /**
     * Gets the Project name.
     * @return A string representing the project name.
     */
    public String getProjectName()
    {
        return name;
    }

    /**
     * Gets the project ID.
     * @return An integer representing the project ID.
     */
    public int getProjectID()
    {
        return projectID;
    }

    /**
     * Gets the project description.
     * @return A string representing the project description.
     */
    public String getProjectDescription()
    {
        return description;
    }

    /**
     * Gets the requirement list of the project.
     * @return A Requirement list of the project.
     */
    public RequirementList getRequirements()
    {
        return requirementList;
    }

    /**
     * Gets the team members participating in the project.
     * @return An array representing the project team members.
     */
    public TeamMember[] getTeamMembers() {
        return projectTeam.toArray(new TeamMember[0]);
    }

    /**
     * Gets the scrum master of the project.
     * @return An TeamMember object representing the scrum master.
     */
    public TeamMember getScrumMaster() {
        return scrumMaster;
    }

    /**
     * Gets the product owner of the project.
     * @return An TeamMember object representing the product owner.
     */
    public TeamMember getProductOwner() {
        return productOwner;
    }

    /*
     * Gets the productivity of team member that equals to the ratio of estimated time and actual time spent on tasks.
     * @param teamMember The team member which we want the productivity for.
     * @return A float value representing the ratio of estimated time of ended tasks and the spent time of ended tasks.
     * @throws Exception when the team member is not in the project we want to get productivity for.

    public float getProductivityOfMember(TeamMember teamMember) throws Exception
    {
        // not finished
        int spendTimeInTotal = 0;
        int estimatedTimeInTotal = 0;

        if (!(projectTeam.contains(teamMember)))
        {
            throw new Exception("teamMember is not in this project");
        }

        Requirement[] requirements = getRequirements().getAllRequirements();

        for (int i = 0; i < requirements.length; i++)
        {
            Task[] tasks = requirements[i].getTasks().getAllTasks();

            for (int j = 0; j < tasks.length; j++)
            {
                if (tasks[i].getStatus().equals(Status.ENDED))
                {
                    ArrayList<TeamMember> taskTeamMembers
                            = new ArrayList<>(Arrays.asList(tasks[i].getTeamMembers()));

                    if (taskTeamMembers.contains(teamMember))
                    {
                        spendTimeInTotal += tasks[i].getTimeSpentOfMember(teamMember);
                        estimatedTimeInTotal += tasks[i].getEstimatedTime();
                    }
                }
            }
        }
                
        return (float) (estimatedTimeInTotal / spendTimeInTotal);
    }*/

    /**
     * Adds a team member to the project team, only if the team member is not already added.
     * @param teamMember Represents the team member you want to add.
     */
    public void addTeamMember(TeamMember teamMember)
    {
        for (int i = 0; i < getTeamMembers().length; i++)
            if (getTeamMembers()[i].equals(teamMember))
                throw new IllegalArgumentException("The team member is already added");

        projectTeam.add(teamMember);
    }

    /**
     * Removes a team member from ArrayList of team members.
     * @param teamMember Represents the team member you want to remove.
     */
    public void removeTeamMember(TeamMember teamMember)
    {
        projectTeam.remove(teamMember);
    }

    /**
     * Sets the name of the project.
     * @param name Contains the name you want to set it to.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description of the project.
     * @param description Contains the description you want to set it to.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the scrumMaster of the project.
     * @param scrumMaster Contains the scrumMaster you want to set it to.
     */
    public void setScrumMaster(TeamMember scrumMaster) {
        this.scrumMaster = scrumMaster;
    }

    /**
     * Sets the productOwner of the project.
     * @param productOwner Contains the productOwner you want to set it to.
     */
    public void setProductOwner(TeamMember productOwner) {
        this.productOwner = productOwner;
    }


    /*public String toString()
    {
        String s = "";

        return
    }*/

    /**
     * Returns a string with all the info.
     * @return A string representing all the project info.
     */
    @Override
    public String toString() {
        return "Project " +
                "name - '" + name +
                ", projectID - " + projectID +
                ", description - '" + description +
                ", scrumMaster - " + scrumMaster +
                ", productOwner - " + productOwner +
                ", projectTeam - " + projectTeam +
                ", requirementList - " + requirementList;
    }
}
