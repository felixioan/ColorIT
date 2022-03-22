package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest
{
  private Project project;
  @BeforeEach void setUp()
  {
    this.project = new Project("Test Project", "Test Project Description");
  }

  @AfterEach void tearDown()
  {
    this.project = new Project("Test Project", "Test Project Description");
  }

  @Test void addTeamMember()
  {
    TeamMember tempTeamMember = new TeamMember("John", "john@doe.com");
    this.project.addTeamMember(tempTeamMember);
    assertEquals("john@doe.com", this.project.getTeamMembers()[0].getEmail());
  }

  @Test void removeTeamMember()
  {
    TeamMember tempTeamMember = new TeamMember("John", "john@doe.com");
    this.project.addTeamMember(tempTeamMember);
    this.project.removeTeamMember(tempTeamMember);
    assertEquals(0, this.project.getTeamMembers().length);
  }

  @Test void setName()
  {
    this.project.setName("Test Project 2");
    assertEquals("Test Project 2", this.project.getProjectName());
  }

  @Test void setDescription()
  {
    this.project.setDescription("Test Project Description 2");
    assertEquals("Test Project Description 2", this.project.getProjectDescription());
  }

  @Test void setScrumMaster()
  {
    TeamMember tempTeamMember = new TeamMember("John", "john@doe.com");
    this.project.setScrumMaster(tempTeamMember);
    assertEquals("john@doe.com", this.project.getScrumMaster().getEmail());
  }

  @Test void setProductOwner()
  {
    TeamMember tempTeamMember = new TeamMember("John", "john@doe.com");
    this.project.setProductOwner(tempTeamMember);
    assertEquals("john@doe.com", this.project.getProductOwner().getEmail());
  }
}