package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest
{
  private TaskList taskList;
  private long testDeadline = new TimeClass().addDays(4).getTime();
  @BeforeEach void setUp()
  {
    Requirement requirement = new Requirement(1,
    "name",
    "description",
    100,
    new TeamMember("John Doe", "john@doe.doe"));
    this.taskList = new TaskList(requirement);
    this.taskList.addTask("Test Task", 1 , 60000, "Test description", testDeadline);
  }

  @AfterEach void tearDown()
  {
    Requirement requirement = new Requirement(1,
    "name",
    "description",
    100,
    new TeamMember("John Doe", "john@doe.doe"));
    this.taskList = new TaskList(requirement);
    this.taskList.addTask("Test Task",1, 60000, "Test description", testDeadline);
  }

  @Test void addTask()
  {
    this.taskList.addTask("Test Task 2", 1, 60000, "Test description 2", testDeadline);
    assertEquals("Test Task 2", this.taskList.getAllTasks()[1].getName());
    assertEquals(2, this.taskList.getAllTasks().length);
  }

  @Test void getTasksByName()
  {
    this.taskList.addTask("Test Task 2",1,  60000, "Test description 2", testDeadline);
    this.taskList.addTask("Test Task", 1,  60000, "Test description", testDeadline);
    assertEquals("Test Task 2", this.taskList.getTasksByName("Test Task 2")[0].getName());
    assertEquals(2, this.taskList.getTasksByName("Test Task").length);
  }

  @Test void getTasksByStatus()
  {
    this.taskList.addTask("Test Task 2",1,  60000, "Test description 2", testDeadline);
    this.taskList.getAllTasks()[1].setStatus(Status.APPROVED);
    assertEquals("Test Task 2", this.taskList.getTasksByStatus(Status.APPROVED)[0].getName());
  }

  @Test void getTasksDaysBeforeDeadline()
  {
    this.taskList.addTask("Test Task 2",1,  60000, "Test description 2", new TimeClass().addDays(1).getTime());
    assertEquals("Test Task 2", this.taskList.getTasksDaysBeforeDeadline(2)[0].getName());
    assertEquals(2, this.taskList.getTasksDaysBeforeDeadline(4).length);
  }

  @Test void getUsedTime()
  {
    assertEquals(0, this.taskList.getUsedTime());
    TeamMember tempTeamMember = new TeamMember("John", "john@doe.com");
    TeamMember tempTeamMember2 = new TeamMember("John2", "john@doe2.com");
    this.taskList.getAllTasks()[0].setTimeWorked(tempTeamMember, 30000);
    this.taskList.getAllTasks()[0].setTimeWorked(tempTeamMember2, 30000);
    assertEquals(60000, this.taskList.getUsedTime());
  }

  @Test void deleteTask()
  {
    this.taskList.addTask("Test Task 2",1, 60000, "Test description 2", new TimeClass().addDays(1).getTime());
    assertEquals(2, this.taskList.getAllTasks().length);
    this.taskList.deleteTask(this.taskList.getAllTasks()[1]);
    assertEquals(1, this.taskList.getAllTasks().length);
  }
}