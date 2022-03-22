package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamMemberTest
{
  private TeamMember teamMember;
  @BeforeEach void setUp()
  {
    this.teamMember = new TeamMember("John", "john@doe.com");
  }

  @AfterEach void tearDown()
  {
    this.teamMember = new TeamMember("John", "john@doe.com");
  }

  @Test void setName()
  {
    this.teamMember.setName("John2");
    assertEquals("John2", this.teamMember.getName());
  }

  @Test void setEmail()
  {
    this.teamMember.setEmail("john@doe2.com");
    assertEquals("john@doe2.com", this.teamMember.getEmail());
  }
}