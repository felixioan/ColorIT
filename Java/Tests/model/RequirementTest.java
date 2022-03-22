package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequirementTest
{
  private Requirement requirement;
  private long testDeadline = new TimeClass().addDays(4).getTime();

  @BeforeEach void setUp()
  {
    TeamMember tempTeamMember = new TeamMember("John", "john@doe.com");
    this.requirement = new Requirement(0, "Test Requirement", "Test description", testDeadline, tempTeamMember);
  }

  @AfterEach void tearDown()
  {
    TeamMember tempTeamMember = new TeamMember("John", "john@doe.com");
    this.requirement = new Requirement(0, "Test Requirement", "Test description", testDeadline, tempTeamMember);
  }

  @Test void setName()
  {
    this.requirement.setName("Test Requirement setName");
    assertEquals("Test Requirement setName", this.requirement.getName());
  }

  @Test void setDeadlineTime()
  {
    long tempDeadlineTime = new TimeClass().getTime();
    this.requirement.setDeadlineTime(tempDeadlineTime);
    assertEquals(tempDeadlineTime, this.requirement.getDeadlineTime());
  }

  @Test void setDescription()
  {
    this.requirement.setDescription("Test description setDescription");
    assertEquals("Test description setDescription", this.requirement.getDescription()[0]);
  }

  @Test void setResponsibleTeamMember()
  {
    TeamMember tempTeamMember2 = new TeamMember("John2", "john@doe2.com");
    this.requirement.setResponsibleTeamMember(tempTeamMember2);
    assertEquals("john@doe2.com", this.requirement.getResponsibleTeamMember().getEmail());
  }

  @Test void setStatus()
  {
    this.requirement.setStatus(Status.APPROVED);
    assertEquals(Status.APPROVED, this.requirement.getStatus());
  }
}