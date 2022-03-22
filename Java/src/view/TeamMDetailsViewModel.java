package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Project;
import model.ProjectManagementModel;
import model.TeamMember;

public class TeamMDetailsViewModel
{
  private StringProperty projectName;
  private StringProperty roleName;

  public TeamMDetailsViewModel(Project project, TeamMember teamMember, ProjectManagementModel model){
    projectName = new SimpleStringProperty(model.getProjectName(project));
    if (model.getScrumMaster(project) != null && model.getScrumMaster(project).equals(teamMember)){
      roleName = new SimpleStringProperty("scrum master");
    } else if (model.getProductOwner(project) != null && model.getProductOwner(project).equals(teamMember)){
      roleName = new SimpleStringProperty("product owner");
    } else {
      roleName = new SimpleStringProperty("");
    }
  }

  public StringProperty getProjectName()
  {
    return projectName;
  }

  public StringProperty getRoleName(){
    return roleName;
  }
}
