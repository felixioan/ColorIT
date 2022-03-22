package model;

import java.io.Serializable;

/**
 * Contains one team member along with an integer representing the time spend.
 * @author Group 6 - 1Y ICT A2020
 * @version 1.0 - December 2020 - December 2020
 * @since 1.0
 */
public class TrackTime implements Serializable
{
  private int timeSpend = 0;
  private TeamMember teamMember;

  /**
   * Two-argument constructor. Creates a TrackTime object.
   * @param teamMember Contains the team member.
   * @param timeSpend Contains the time spend on the task.
   */
  public TrackTime(TeamMember teamMember, int timeSpend) {
    this.teamMember = teamMember;
    this.timeSpend = timeSpend;
  }

  /**
   * Sets the time worked on the task.
   * @param time Contains the time spend on the task.
   */
  public void setTimeWorked(int time) {
    this.timeSpend = time;
  }

  /**
   * Gets the team member.
   * @return A TeamMember object containing the team member.
   */
  public TeamMember getTeamMember() {
    return  this.teamMember;
  }

  /**
   * Gets the time spend on this task.
   * @return An integer representing the time spend on this task by a team member.
   */
  public int getTime() {
    return this.timeSpend;
  }
}
