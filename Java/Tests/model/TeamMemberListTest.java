package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamMemberListTest
{
  private TeamMemberList teamMemberList;
  @BeforeEach void setUp()
  {
    this.teamMemberList = new TeamMemberList();
  }

  @AfterEach void tearDown()
  {
    this.teamMemberList = new TeamMemberList();
  }

  @Test void addTeamMember()
  {
    this.teamMemberList.addTeamMember("John", "john@doe.com");
    assertEquals("john@doe.com", this.teamMemberList.getAllTeamMembers()[0].getEmail());
  }

  @Test void setTeamMemberEmail()
  {
    this.teamMemberList.addTeamMember("John", "john@doe.com");
    this.teamMemberList.setTeamMemberEmail(this.teamMemberList.getAllTeamMembers()[0], "john@doe2.com");
    assertEquals("john@doe2.com", this.teamMemberList.getAllTeamMembers()[0].getEmail());
  }

  @Test void deleteTeamMember()
  {
    this.teamMemberList.addTeamMember("John", "john@doe.com");
    this.teamMemberList.deleteTeamMember(this.teamMemberList.getAllTeamMembers()[0]);
    assertEquals(0, this.teamMemberList.getAllTeamMembers().length);
  }
}