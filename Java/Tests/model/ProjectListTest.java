package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectListTest
{

  private ProjectList projectList;

  @BeforeEach void setUp()
  {
    this.projectList = new ProjectList();
  }

  @AfterEach void tearDown()
  {
    this.projectList = new ProjectList();
  }

  @Test void addProject()
  {
    this.projectList.addProject("Test Project", "Test project description");
    assertEquals("Test Project", this.projectList.getAllProjects()[0].getProjectName());
    assertEquals("Test project description", this.projectList.getAllProjects()[0].getProjectDescription());
  }

  @Test void getProjectsByName()
  {
    this.projectList.addProject("Test Project 1", "");
    this.projectList.addProject("Test Project 2", "");
    this.projectList.addProject("Test Project 3", "");
    this.projectList.addProject("Test Project 4", "");
    assertEquals("Test Project 3", this.projectList.getProjectsByName("Test project 3")[0].getProjectName());
    assertEquals(0, this.projectList.getProjectsByName("Test project 5").length);
  }

  @Test void getAllProjects()
  {
    this.projectList.addProject("Test Project 1", "");
    this.projectList.addProject("Test Project 2", "");
    assertEquals(2, this.projectList.getAllProjects().length);
  }

  @Test void deleteProject()
  {
    this.projectList.addProject("Test Project", "");
    int projectID = this.projectList.getAllProjects()[0].getProjectID();
    this.projectList.deleteProject(projectID);
    assertEquals(0, this.projectList.getAllProjects().length);
  }
}