package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest
{
  private Task task;
  private long testDeadline = new TimeClass().addDays(4).getTime();
  @BeforeEach void setUp()
  {
    TaskList taskList = new TaskList(new Requirement(1,
            "name",
            "description",
            100,
            new TeamMember("John Doe", "john@doe.doe")));
    this.task = new Task("Test Task",1,  1, 60000, "Test description", testDeadline, taskList);
  }

  @AfterEach void tearDown()
  {
    TaskList taskList = new TaskList(new Requirement(1,
            "name",
            "description",
            100,
            new TeamMember("John Doe", "john@doe.doe")));
    this.task = new Task("Test Task",1,  1, 60000, "Test description", testDeadline, taskList);
  }

  @Test void getTimeSpent()
  {
    TeamMember tempTeamMember = new TeamMember("John", "john@doe.com");
    this.task.setTimeWorked(tempTeamMember, 30000);
    TeamMember tempTeamMember2 = new TeamMember("John2", "john@doe2.com");
    this.task.setTimeWorked(tempTeamMember2, 30000);
    assertEquals(60000, this.task.getTimeSpent());
  }

  @Test void addTeamMember()
  {
    TeamMember tempTeamMember = new TeamMember("John", "john@doe.com");
    assertEquals(0, this.task.getTeamMembers().length);
    this.task.addTeamMember(tempTeamMember);
    assertEquals("john@doe.com", this.task.getTeamMembers()[0].getEmail());
  }

  @Test void setName()
  {
    this.task.setName("Test Task setName");
    assertEquals("Test Task setName", this.task.getName());
  }

  @Test void setDescription()
  {
    this.task.setDescription("Test description setDescription");
    assertEquals("Test description setDescription", this.task.getDescription());
  }

  @Test void setDeadlineTime()
  {
    long testDeadlineTime = new TimeClass().getTime();
    this.task.setDeadlineTime(testDeadlineTime);
    assertEquals(testDeadlineTime, this.task.getDeadlineTime());
  }

  @Test void setEstimatedTime()
  {
    this.task.setEstimatedTime(30000);
    assertEquals(30000, this.task.getEstimatedTime());
  }

  @Test void setResponsibleTeamMember()
  {
    TeamMember tempTeamMember = new TeamMember("John", "john@doe.com");
    this.task.setResponsibleTeamMember(tempTeamMember);
    assertEquals("john@doe.com", this.task.getResponsibleTeamMember().getEmail());
  }

  @Test void setStatus()
  {
    this.task.setStatus(Status.APPROVED);
    assertEquals(Status.APPROVED, this.task.getStatus());
  }

  @Test void setTimeWorked()
  {
    TeamMember tempTeamMember = new TeamMember("John", "john@doe.com");
    this.task.setTimeWorked(tempTeamMember, 30000);
    assertEquals(30000, this.task.getTimeSpentOfMember(tempTeamMember));
  }
}