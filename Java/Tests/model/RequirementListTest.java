package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequirementListTest
{
  private RequirementList requirementList;
  private long testDeadline = new TimeClass().addDays(4).getTime();
  @BeforeEach void setUp()
  {
    this.requirementList = new RequirementList();
    TeamMember tempTeamMember = new TeamMember("John", "john@doe.com");
    this.requirementList.addRequirement("Test Requirement", "Test description", testDeadline, tempTeamMember);
  }

  @AfterEach void tearDown()
  {
    this.requirementList = new RequirementList();
    TeamMember tempTeamMember = new TeamMember("John", "john@doe.com");
    this.requirementList.addRequirement("Test Requirement", "Test description", testDeadline, tempTeamMember);
  }

  @Test void addRequirement()
  {
    TeamMember tempTeamMember2 = new TeamMember("John2", "john@doe2.com");
    this.requirementList.addRequirement("Test Requirement 2", "Test description 2", testDeadline, tempTeamMember2);
    assertEquals(2, this.requirementList.getAllRequirements().length);
  }

  @Test void getRequirementsByStatus()
  {
    TeamMember tempTeamMember2 = new TeamMember("John2", "john@doe2.com");
    this.requirementList.addRequirement("Test Requirement 2", "Test description 2", testDeadline, tempTeamMember2);
    this.requirementList.getRequirementByName("Test Requirement 2")[0].setStatus(Status.STARTED);
    assertEquals("Test Requirement 2", this.requirementList.getRequirementsByStatus(Status.STARTED)[0].getName());
    assertEquals(0, this.requirementList.getRequirementsByStatus(Status.APPROVED).length);
  }

  @Test void getRequirementsBeforeDeadline()
  {
    TeamMember tempTeamMember2 = new TeamMember("John2", "john@doe2.com");
    this.requirementList.addRequirement("Test Requirement 2", "Test description 2", new TimeClass().addDays(1).getTime(), tempTeamMember2);
    assertEquals("Test Requirement 2", this.requirementList.getRequirementsBeforeDeadline(2)[0].getName());
    assertEquals(2, this.requirementList.getRequirementsBeforeDeadline(4).length);
  }

  @Test void getRequirementByResponsibleTeamMember()
  {
    TeamMember tempTeamMember2 = new TeamMember("John2", "john@doe2.com");
    TeamMember tempTeamMember3 = new TeamMember("John3", "john@doe3.com");
    TeamMember tempTeamMember4 = new TeamMember("John4", "john@doe4.com");
    this.requirementList.addRequirement("Test Requirement 2", "Test description 2", new TimeClass().addDays(-1).getTime(), tempTeamMember2);
    this.requirementList.getAllRequirements()[1].setResponsibleTeamMember(tempTeamMember3);
    assertEquals("Test Requirement 2", this.requirementList.getRequirementByResponsibleTeamMember(tempTeamMember3)[0].getName());
    assertEquals(0, this.requirementList.getRequirementByResponsibleTeamMember(tempTeamMember4).length);
  }

  @Test void getRequirementByID()
  {
    TeamMember tempTeamMember2 = new TeamMember("John2", "john@doe2.com");
    this.requirementList.addRequirement("Test Requirement 2", "Test description 2", new TimeClass().addDays(-1).getTime(), tempTeamMember2);
    int testRequirementID = this.requirementList.getAllRequirements()[1].getRequirementId();
    assertEquals("Test Requirement 2", this.requirementList.getRequirementByID(testRequirementID).getName());
  }

  @Test void getRequirementByName()
  {
    TeamMember tempTeamMember2 = new TeamMember("John2", "john@doe2.com");
    this.requirementList.addRequirement("Test Requirement 2", "Test description 2", new TimeClass().addDays(-1).getTime(), tempTeamMember2);
    assertEquals("Test Requirement 2", this.requirementList.getRequirementByName("Test Requirement 2")[0].getName());
  }

  @Test void deleteRequirement()
  {
    TeamMember tempTeamMember2 = new TeamMember("John2", "john@doe2.com");
    this.requirementList.addRequirement("Test Requirement 2", "Test description 2", new TimeClass().addDays(-1).getTime(), tempTeamMember2);
    this.requirementList.deleteRequirement(this.requirementList.getAllRequirements()[1]);
    assertEquals(1, this.requirementList.getAllRequirements().length);
  }

  @Test void reorderRequirements()
  {
    TeamMember tempTeamMember2 = new TeamMember("John2", "john@doe2.com");
    this.requirementList.addRequirement("Test Requirement 2", "Test description 2", new TimeClass().addDays(-1).getTime(), tempTeamMember2);
    this.requirementList.reorderRequirements(1, 0);
    assertEquals("Test Requirement 2", this.requirementList.getAllRequirements()[0].getName());
  }
}