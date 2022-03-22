package model;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Accumulates Requirement class objects in an ArrayList requirements.
 * @author Group 6 - 1Y ICT A2020
 * @version 1.0 - December 2020
 * @since 1.0
 */
public class RequirementList implements Serializable
{
  /**
   * Represents the ArrayList of Requirement class objects.
   * Holds the requirements of one project.
   */
  private ArrayList<Requirement> requirements;
  /**
   * Represents the number of requirements created in this project.
   * Used in ID assigning.
   */
  private int requirementsCreated = 1;

  /**
   * Zero-argument constructor.
   * Creates the ArrayList of Requirement class objects
   */
  public RequirementList()
  {
    this.requirements = new ArrayList<Requirement>();
  }

  /**
   * Creates a new Requirement in the ArrayList of Requirement class, requirements.
   * The requirement gets an auto assigned Project-wide unique ID, based on the number of requirements created.
   * @param name A string representing the name of the requirement, trimmed of any spaces.
   * @param nonFunctionalDescription A string representing a non functional description.
   * @param deadline A long representing the time of the deadline with a UNIX timestamp.
   * @param responsibleTeamMember A TeamMember object representing the responsible team member.
   */
  public void addRequirement(String name, String nonFunctionalDescription, long deadline, TeamMember responsibleTeamMember)
  {
    if (name.trim().equals(""))
      throw new IllegalArgumentException("Invalid name");
    else
      {
      requirements.add(new Requirement(this.requirementsCreated, name.trim(), nonFunctionalDescription, deadline, responsibleTeamMember));
      this.requirementsCreated++;
    }
  }

  /**
   * Creates a new Requirement in the ArrayList of Requirement class, requirements.
   * @param name A string representing the name of the requirement, trimmed of any spaces.
   * @param FunctionalDescription An array of strings representing a functional description in "who, what, why" template.
   * @param deadline A long representing the time of the deadline with a UNIX timestamp.
   * @param responsibleTeamMember A TeamMember object representing the responsible team member.
   */
  public void addRequirement(String name, String[] FunctionalDescription, long deadline, TeamMember responsibleTeamMember)
  {
    if (name.trim().equals(""))
      throw new IllegalArgumentException("Invalid name");
    else
      {
      requirements.add(new Requirement(this.requirementsCreated, name.trim(), FunctionalDescription, deadline, responsibleTeamMember));
      this.requirementsCreated++;
    }
  }

  /**
   * Gets all requirements of this RequirementList
   * @return An array of Requirement objects representing all the requirements <br> of this RequirementList(of Project the RequirementList belongs to)
   */
  public Requirement[] getAllRequirements() {
    return this.requirements.toArray(new Requirement[0]);
  }

  /**
   * Gets all requirements with a specified status.
   * @param status A String representing the status of the requirements we want.
   * @return An array of Requirement objects representing requirements with a specified status.
   */
  public Requirement[] getRequirementsByStatus(String status) {
    ArrayList<Requirement> foundRequirements = new ArrayList<Requirement>();
    for (Requirement requirement : this.requirements)
    {
      if (requirement.getStatus().equals(status)) {
        foundRequirements.add(requirement);
      }
    }
    return foundRequirements.toArray(new Requirement[0]);
  }

  /**
   * Gets requirements that are closer to the deadline than a specified time.
   * @param days An integer representing the number of days,<br> specifies how close the to the deadline we want the requirements.
   * @return An array of Requirement objects that are closer to the deadline <br> than the specified number of days.
   */
  public Requirement[] getRequirementsBeforeDeadline(int days)
  {
    // TODO
    ArrayList<Requirement> requirementsBeforeDeadline = new ArrayList<>();

    long currentTime = new TimeClass().getTime();

    for (int i = 0; i < requirements.size(); i++)
    {
      long deadline = requirements.get(i).getDeadlineTime();

      if (new TimeClass(deadline).addDays(-days).getTime() <= currentTime)
        requirementsBeforeDeadline.add(requirements.get(i));
    }

    return requirementsBeforeDeadline.toArray(new Requirement[0]);
  }

  /**
   * Gets requirements with a specified responsible member.
   * @param responsible A TeamMember object representing the responsible team member we want to filter by.
   * @return An array of Requirement objects representing requirements with a specified team member.
   */
  public Requirement[] getRequirementByResponsibleTeamMember(TeamMember responsible) {
    ArrayList<Requirement> foundRequirements = new ArrayList<Requirement>();
    for (Requirement requirement : this.requirements)
    {
      if (requirement.getResponsibleTeamMember().equals(responsible)) {
        foundRequirements.add(requirement);
      }
    }
    return foundRequirements.toArray(new Requirement[0]);
  }

  /**
   * Gets a requirement with a specific ID.
   * @param requirementID An integer representing the ID of the requirement.
   * @return A Requirement object that has the id fitting the parameter.
   */
  public Requirement getRequirementByID(int requirementID) {
    for (Requirement requirement : this.requirements)
    {
      if (requirement.getRequirementId()==requirementID) {
        return requirement;
      }
    }
    return null;
  }

  /**
   * Gets all requirements with a specified name.
   * @param name A String representing the name of the wanted requirements.
   * @return An array of Requirement objects representing requirements with the same name as the input.
   */
  public Requirement[] getRequirementByName(String name) {
    ArrayList<Requirement> foundRequirements = new ArrayList<Requirement>();
    for (Requirement requirement : this.requirements)
    {
      if (requirement.getName().trim().equalsIgnoreCase(name))
        foundRequirements.add(requirement);
    }
    return foundRequirements.toArray(new Requirement[0]);
  }

  /**
   * Deletes a requirement.
   * @param requirement Represents the requirement we want to delete.
   */
  public void deleteRequirement(Requirement requirement) {
    this.requirements.remove(requirement);
  }

  /**
   * Reorders two requirements, swaps their places in the ArrayList.
   * @param index1 An integer representing a position of the first requirement.
   * @param index2 An integer representing a position of the second requirement.
   */
  public void reorderRequirements(int index1, int index2)
  {
    Collections.swap(this.requirements, index1, index2);
  }
}
