package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a Task.
 * Has a name, ID, description, estimated time to finish from start,
 * deadline, responsible member, status and time tracking for all members that did any work.
 * @author Group 6 - 1Y ICT A2020
 * @version 1.0 - December 2020
 * @since 1.0
 */
public class Task implements Serializable
{

  private int id;
  private int requirementId;
  private String name;
  private String description;
  private int estimatedTime;
  private long deadlineTime;
  private TeamMember responsibleTeamMember;
  private String status;
  private TrackTimeList trackTimeList;
  private TaskList parentTaskList;

  /**
   * Six-argument constructor.
   * @param name A String representing the name of the task.
   * @param requirementId  Id of requirement that it belongs to.
   * @param id An integer representing an ID, assigned automatically by the TaskList class.
   * @param estimatedTime An integer representing the estimated time to finish the task from start to the end.
   * @param description A string representing the description.
   * @param deadlineTime A long representing the time of the deadline with a UNIX timestamp.
   * @param parentTaskList A TaskList representing the TaskList which holds the task.
   */
  public Task(String name,int requirementId, int id, int estimatedTime, String description, long deadlineTime, TaskList parentTaskList) {
    this.id=id;
    this.requirementId = requirementId;
    this.name=name;
    this.description=description;
    this.deadlineTime=deadlineTime;
    this.estimatedTime=estimatedTime;
    this.status = Status.NOT_STARTED;
    this.trackTimeList = new TrackTimeList();
    this.parentTaskList = parentTaskList;
  }

  /**
   * Sevens-argument constructor.
   * @param name A String representing the name of the task.
   * @param id An integer representing an ID, assigned automatically by the TaskList class.
   * @param estimatedTime An integer representing the estimated time to finish the task from start to the end.
   * @param description A string representing the description.
   * @param deadlineTime A long representing the time of the deadline with a UNIX timestamp.
   * @param responsibleTeamMember A TeamMember object representing the responsible team member.
   * @param parentTaskList A TaskList representing the TaskList which holds the task.
   */
  public Task(String name, int requirementId, int id, int estimatedTime, String description, long deadlineTime, TeamMember responsibleTeamMember, TaskList parentTaskList) {
    this.id=id;
    this.requirementId = requirementId;
    this.name=name;
    this.description=description;
    this.deadlineTime=deadlineTime;
    this.estimatedTime=estimatedTime;
    this.responsibleTeamMember=responsibleTeamMember;
    this.status = Status.NOT_STARTED;
    this.trackTimeList = new TrackTimeList();
    this.parentTaskList = parentTaskList;
  }

  /**
   * Gets the ID of the task.
   * @return An integer representing the ID.
   */
  public int getTaskID() {
    return this.id;
  }
  /**
   * Gets the ID of the Requirement.
   * @return An integer representing the requirement ID.
   */
  public int getRequirementID()
  {
    return requirementId;
  }

  /**
   * Gets the name.
   * @return A string representing the name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the description.
   * @return A string representing the description.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets the estimated time.
   * @return An integer representing the estimated time.
   */
  public int getEstimatedTime() {
    return this.estimatedTime;
  }


  /**
   * Gets the deadline time.
   @return A long representing the date of the deadline as a UNIX timestamp.
   */
  public long getDeadlineTime() {
    return this.deadlineTime;
  }

  /**
   * Gets the total time spend on this task, by all team members together.
   * @return An integer representing the time spend.
   */
  public int getTimeSpent() {
    return this.trackTimeList.getTotalTime();
  }

  /**
   * Gets the time spend of one team member.
   * @param teamMember Specifies the team member.
   * @return An integer representing the time spend.
   */
  public int getTimeSpentOfMember(TeamMember teamMember) {
    return this.trackTimeList.getTimeOfMember(teamMember);
  }

  /**
   * Gets all the team members working on this task.
   * @return An array of team members that worked on the task.
   */
  public TeamMember[] getTeamMembers()
  {
    ArrayList<TeamMember> teamMembers = new ArrayList<>();

    for (int i = 0; i < trackTimeList.getTrackTime().size(); i++)
      teamMembers.add(trackTimeList.getTrackTime().get(i).getTeamMember());

    return teamMembers.toArray(new TeamMember[0]);
  }

  /**
   * Gets the responsible team member.
   * @return Responsible team member for the task.
   */
  public TeamMember getResponsibleTeamMember() {
    return this.responsibleTeamMember;
  }

  /**
   * Gets the status of the task.
   * @return A string representing the status of the task.
   */
  public String getStatus()
  {
    return this.status;
  }

  /**
   * Gets the whole TrackTimeList into an track time array.
   * @return An array of TrackTime.
   */
  public TrackTime[] getTrackTime()
  {
    return this.trackTimeList.getTrackTime().toArray(new TrackTime[0]);
  }

  /**
   * Adds a team member to the track time with 0 starting time, only if the team member is not already added.
   * @param teamMember Specifies which team member should be added.
   */
  public void addTeamMember(TeamMember teamMember)
  {
    for (int i = 0; i < getTeamMembers().length; i++)
      if (getTeamMembers()[i].equals(teamMember))
        throw new IllegalArgumentException("The team member is already added");

    trackTimeList.setTimeWorked(teamMember, 0);
  }

  /**
   * Sets the name, to a trimmed form of the input.
   * @param name A string containing the name, which is then trimmed.
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
   * Sets the description.
   * @param description A string containing the description.
   */
  public void setDescription(String description) {
    this.description=description;
  }

  /**
   * Sets the deadline time,
   * @param time A long containing a new deadline date as a UNIX timestamp.
   */
  public void setDeadlineTime(long time) {
    this.deadlineTime=time;
  }

  /**
   * Sets the estimated time to finish the task.
   * @param estimatedTime An integer containing the estimated time.
   */
  public void setEstimatedTime(int estimatedTime) {
    this.estimatedTime=estimatedTime;
  }

  /**
   * Sets the responsible team member.
   * @param teamMember A TeamMember object containing the responsible team member.
   */
  public void setResponsibleTeamMember(TeamMember teamMember) {
    this.responsibleTeamMember=teamMember;
  }

  /**
   * Sets the tasks status. If all the tasks have ended status, the requirement will be set to ended.
   * @param status A Status containing the status.
   */
  public void setStatus(String status) {
    this.status = status;


      if (status.equals(Status.ENDED)
              && (parentTaskList.getTasksByStatus(Status.ENDED).length
              == parentTaskList.getAllTasks().length))
        parentTaskList.getParentRequirement().setStatus(Status.ENDED);
  }

  /**
   * Sets the time worked for a team member.
   * @param teamMember Specifies the team member the time will be set for.
   * @param time Contains the new time the member has worked
   */
  public void setTimeWorked(TeamMember teamMember, int time) {
    this.trackTimeList.setTimeWorked(teamMember, time);
  }

  /**
   * does nothing
   * @return empty string
   */
  public String toString() {
    return "";
  }
}
