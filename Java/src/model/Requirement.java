package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a Requirement.
 * Has a name, ID, description,deadline,
 * responsible member, status and holds its tasks in a TaskList.
 * @author Group 6 - 1Y ICT A2020
 * @version 1.0 - December 2020 - December 2020
 * @since 1.0
 */
public class Requirement implements Serializable
{
  private int requirementId;
  private String name;
  private long deadlineTime;
  /**
   * Represents a description of the project, chosen by the user.
   * Is either a functionalDescription or nonFunctionalDescription.
   */
  private Description description;
  /**
   * Represents the team member responsible for the requirement.
   */
  private TeamMember responsibleTeamMember;
  /**
   * Represents the status of the requirement.
   * Can be: NOT_STARTED, STARTED, ENDED, APPROVED, REJECTED
   */
  private String status;
  /**
   * Represents the list of task that are part of the requirement and therefore a project, stored in a TaskList class.
   */
  private TaskList tasksList;

  /**
   * Five-argument constructor. Creates a requirement with non functional description, sets the status to not started.
   * Creates a new task list for the requirement.
   * @param id An integer representing an ID, assigned automatically by the RequirementList class.
   * @param name A String representing the name of the requirement.
   * @param description A String representing the nonFunctionalDescription of the requirement.
   * @param deadline A long representing A long representing the time of the deadline with a UNIX timestamp.
   * @param responsibleTeamMember A TeamMember object representing the responsible team member.
   */
  public Requirement(int id, String name, String description, long deadline, TeamMember responsibleTeamMember) {
    this.requirementId = id;
    this.name = name;
    this.deadlineTime = deadline;
    this.responsibleTeamMember = responsibleTeamMember;
    this.status = Status.NOT_STARTED;
    this.description = new nonFunctionalDescription(description);
    this.tasksList = new TaskList(this);
  }

  /**
   * Five-argument constructor. Creates a requirement with functional description, sets the status to not started.
   * Creates a new task list for the requirement.
   * @param id An integer representing an ID, assigned automatically by the RequirementList class.
   * @param name A String representing the name of the requirement.
   * @param description An array of Strings representing the FunctionalDescription of the requirement.
   * @param deadline A long representing A long representing the time of the deadline with a UNIX timestamp.
   * @param responsibleTeamMember A TeamMember object representing the responsible team member.
   */
  public Requirement(int id, String name, String[] description, long deadline, TeamMember responsibleTeamMember) {
    this.requirementId = id;
    this.name = name;
    this.deadlineTime = deadline;
    this.responsibleTeamMember = responsibleTeamMember;
    this.status = Status.NOT_STARTED;
    this.description = new functionalDescription(description);
    this.tasksList = new TaskList(this);
  }

  /**
   * Gets the ID of the requirement.
   * @return An integer representing the ID of the requirement.
   */
  public int getRequirementId() {
    return this.requirementId;
  }

  /**
   * Gets the name of the requirement.
   * @return A String representing the name of the requirement.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the deadline time in a UNIX timestamp format.
   * @return A long representing the date of the deadline as a UNIX timestamp.
   */
  public long getDeadlineTime() {
    return this.deadlineTime;
  }

  /**
   * Gets the description of the requirement.
   * @return A String array representing the description of the requirement, either functional 3 string, or non functional, 1 string.
   */
  public String[] getDescription() {
    return this.description.getDescription();
  }

  /**
   * Gets the responsible team member.
   * @return An TeamMember object representing the responsible team member.
   */
  public TeamMember getResponsibleTeamMember() {
    return this.responsibleTeamMember;
  }

  /**
   * Gets the status of the requirement.
   * @return An String representing the status.
   */
  public String getStatus() {
    return this.status;
  }

  /**
   * Gets the total time spend on the requirement.
   * @return An integer representing the total time spend on the requirement.
   */
  public int getUsedTime() {
    return this.tasksList.getUsedTime();
  }

  /**
   * Gets a boolean statement saying if the requirement is functional.
   * @return A boolean representing statement if the requirement is functional, true if it is, false if isn't.
   */
  public boolean isFunctional() {
    return this.description.isFunctional();
  }

  /**
   * Gets all tasks, belonging to a requirement.
   * @return A TaskList object representing the tasks of this requirement.
   */
  public TaskList getTasks()
  {
    return this.tasksList;
  }

  /**
   * Gets the estimated time to complete a requirement.
   * @return An integer representing the sum of all estimated times of the requirement.
   */
  public int getEstimatedTime()
  {
    Task[] tasks = this.tasksList.getAllTasks();
    int outputTime = 0;

    for (int i = 0; i < tasks.length; i++)
    {
      outputTime += tasks[i].getEstimatedTime();
    }

    return outputTime;
  }

  /**
   * Sets the requirements name.
   * @param name A String containing the requirement name.
   */
  public void setName(String name)
  {
    if (name.trim().equals(""))
      throw new IllegalArgumentException("Invalid name");
    else
      {
      this.name=name.trim();
    }

  }

  /**
   * Sets the deadline time of the requirement.
   * @param newTime A long containing a new deadline date as a UNIX timestamp.
   */
  public void setDeadlineTime(long newTime) {
    this.deadlineTime=newTime;
  }

  /**
   * Sets the requirements description.
   * @param description A String containing the requirements non functional description.
   */
  public void setDescription(String description)
  {
    //we know that the user want to create non-functional description so we if we had non-functional description before we cas just set it
    if (this.description.isFunctional())
    {
      this.description = new nonFunctionalDescription(description);
    }
    else
      {
        ((nonFunctionalDescription) this.description).setNonFunctionalDescription(description); // I have cast the description to non functional and than I called set function
      }
  }

  /**
   * Sets the requirements description.
   * @param description A String array containing the requirements  functional description.
   */
  public void setDescription(String[] description)
  { //now we know that we got functional description, let's check if we had functional description before
    if (this.description.isFunctional())
    {
      ((functionalDescription) this.description).setFunctionalDescription(description[0],description[1],description[2]);
    }
    else
      {
        this.description = new functionalDescription(description); // I have cast the description to non functional and than I called set function
      }
  }

  /**
   * Sets the requirements responsible team member.
   * @param teamMember A TeamMember object containing the responsible team member.
   */
  public void setResponsibleTeamMember(TeamMember teamMember) {
    this.responsibleTeamMember=teamMember;
  }

  /**
   * Sets the requirements status.
   * @param status A Status containing the status.
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Gets empty string.
   * @return A String representing nothing.
   */
  public String toString() {
    return "";
  }
}
