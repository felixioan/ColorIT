package model;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;

/**
 * Accumulates Task class objects in an ArrayList tasks
 * @author Group 6 - 1Y ICT A2020
 * @version 1.0 - December 2020
 * @since 1.0
 */
public class TaskList implements Serializable
{
  /**
   * Represents the ArrayList of Task class objects.
   * Stores the tasks of one requirement.
   */
  private ArrayList<Task> tasks;

  /**
   * Represents the number of tasks created in this requirement.
   * Used in ID assigning.
   */
  private int tasksCreated = 0;

  private Requirement parentRequirement;

  /**
   * One-argument constructor.
   * Creates the ArrayList of Task class objects
   * @param parentRequirement Representing the parent requirement which this TaskList belongs to.
   */
  public TaskList(Requirement parentRequirement)
  {
    this.tasks = new ArrayList<Task>();
    this.parentRequirement = parentRequirement;
  }

  /**
   * Creates a new Task in the ArrayList of Task class, tasks.
   * The Task gets an auto assigned Requirement-wide unique ID, based on the number of tasks created.
   * @param name A string representing the name of the task, trimmed of any spaces.
   * @param requirementId id of requirement that it belongs to.
   * @param estimatedTime an int representing the estimated time to finish the task from start to the end.
   * @param description A string representing the description.
   * @param deadlineTime A long representing the time of the deadline with a UNIX timestamp.
   */
  public void addTask(String name, int requirementId, int estimatedTime, String description, long deadlineTime)
  {
    if (name.trim().equals(""))
      throw new IllegalArgumentException("Invalid name");
    else
      {
        this.tasksCreated++;
        tasks.add(new Task(name,requirementId, this.tasksCreated, estimatedTime, description, deadlineTime, this));
      }
  }

  /**
   * Creates a new Task in the ArrayList of Task class, tasks.
   * The Task gets an auto assigned Requirement-wide unique ID, based on the number of tasks created.
   * @param name A string representing the name of the task, trimmed of any spaces.
   * @param requirementId id of requirement that it belongs to.
   * @param estimatedTime an int representing the estimated time to finish the task from start to the end.
   * @param description A string representing the description.
   * @param deadlineTime A long representing the time of the deadline with a UNIX timestamp.
   * @param responsibleTeamMember A TeamMember object representing the responsible team member.
   */
  public void addTask(String name,int requirementId, int estimatedTime, String description, long deadlineTime, TeamMember responsibleTeamMember)
  {
    if (name.trim().equals(""))
      throw new IllegalArgumentException("Invalid name");
    else
      {
        this.tasksCreated++;
        tasks.add(new Task(name, requirementId, this.tasksCreated, estimatedTime, description, deadlineTime, responsibleTeamMember, this));
      }
  }

  /**
   * Gets all tasks of this TaskList.
   * @return An array of Task objects.
   */
  public Task[] getAllTasks() {
    return this.tasks.toArray(new Task[0]);
  }

  /**
   * Gets all tasks with same name as the input string.
   * @param name A String representing the name of the wanted tasks.
   * @return An array of Task objects with the same name as the input
   */
  public Task[] getTasksByName(String name)
  {
    ArrayList<Task> foundTasks = new ArrayList<Task>();
    for (Task task : this.tasks)
    {
      if (task.getName().equalsIgnoreCase(name)) {
        foundTasks.add(task);
      }
    }
    return foundTasks.toArray(new Task[0]);
  }

  /**
   * Gets all requirements with a specified status.
   * @param status A String representing the status of the tasks we want.
   * @return An array of Tasks objects with a specified status.
   */
  public Task[] getTasksByStatus(String status)
  {
    ArrayList<Task> foundTasks = new ArrayList<Task>();
    for (Task task : this.tasks)
    {
      if (task.getStatus().equals(status)) {
        foundTasks.add(task);
      }
    }
    return foundTasks.toArray(new Task[0]);
  }

  /**
   * Gets all tasks that are closer to the deadline than a specified time.
   * @param days An integer representing the number of days,<br> specifies how close the to the deadline we want the tasks.
   * @return An array of Task objects are closer to the deadline <br> than the specified number of days.
   */
  public Task[] getTasksDaysBeforeDeadline(int days)
  {
    // not finished
    //TODO
    ArrayList<Task> tasksBeforeDeadline = new ArrayList<>();

    long currentTime = new TimeClass().getTime();

    for (int i = 0; i < tasks.size(); i++)
    {
      long deadline = tasks.get(i).getDeadlineTime();

      if (new TimeClass(deadline).addDays(-days).getTime() <= currentTime)
        tasksBeforeDeadline.add(tasks.get(i));
    }

    return tasksBeforeDeadline.toArray(new Task[0]);
  }

  /**
   * Gets the total time spend on all the tasks in the TaskList.
   * @return An integer representing the time spend.
   */
  public int getUsedTime() {
    int totalTime = 0;
    for (Task task : this.tasks)
    {
      totalTime+=task.getTimeSpent();
    }
    return  totalTime;
  }

  /**
   * Deletes a task.
   * @param task Specifies the task to delete.
   */
  public void deleteTask(Task task) {
    this.tasks.remove(task);
  }

  /**
   * Gets the requirement this taskList belongs to.
   * @return A Requirement object representing the requirement.
   */
  public Requirement getParentRequirement()
  {
    return parentRequirement;
  }
}
