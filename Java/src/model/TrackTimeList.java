package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Accumulates TrackTime class objects in an Arraylist trackTImeList.
 * @author Group 6 - 1Y ICT A2020
 * @version 1.0 - December 2020 - December 2020
 * @since 1.0
 */
public class TrackTimeList implements Serializable
{
    private ArrayList<TrackTime> trackTimeList;

    /**
     * Zero-argument constructor.
     * Creates the ArrayList of TimeTrack class objects.
     */
    public TrackTimeList()
    {
        this.trackTimeList = new ArrayList<TrackTime>();
    }

    /**
     * Sets the time worked for a specified team member. Calls the .setTimeWorked from TimeTrack class.
     * @param teamMember A TeamMember containing the team member we want to change time for.
     * @param time An integer containing the time tha he worked on the task in total.
     */
    public void setTimeWorked(TeamMember teamMember, int time)
    {
        for (TrackTime trackTime : this.trackTimeList)
        {
            if (trackTime.getTeamMember().equals(teamMember))
            {
                trackTime.setTimeWorked(time);
                return;
            }
        }
        this.trackTimeList.add(new TrackTime(teamMember, time));
    }

    /**
     * Gets the TrackTime arraylist.
     * @return An ArrayList containing all the TrackTime objects in this TrackTimeList.
     */
    public ArrayList<TrackTime> getTrackTime()
    {
        return this.trackTimeList;
    }

    /**
     * Gets the time worked on the task of a specified member.
     * @param teamMember Contains the team member to get time worked for.
     * @return An integer representing the time worked on the task by a specified team member.
     * Returns 0 if the team member either didnt work or if he is not in this task
     */
    public int getTimeOfMember(TeamMember teamMember)
    {
        for (TrackTime trackTime : this.trackTimeList)
        {
            if (trackTime.getTeamMember().equals(teamMember))
            {
                return trackTime.getTime();
            }
        }
        return 0;
    }

    /**
     * Gets the sum of all the time worked by all the team members in the TaskList.
     * @return An integer representing the sum.
     */
    public int getTotalTime()
    {
        int totalTime = 0;
        for (TrackTime trackTime : this.trackTimeList)
        {
            totalTime+=trackTime.getTime();
        }
        return totalTime;
    }
}
