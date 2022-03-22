package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Project;
import model.ProjectManagementModel;
import model.TeamMember;

public class TeamMDetailsListViewModel
{
  private ObservableList<TeamMDetailsViewModel> projectList;
  private ProjectManagementModel model;
  private ViewHandler viewHandler;

  public TeamMDetailsListViewModel(ProjectManagementModel model, ViewHandler viewHandler){
    this.model = model;
    this.projectList = FXCollections.observableArrayList();
    this.viewHandler = viewHandler;
    update();
  }

  public void update(){
    this.projectList.clear();
    Project[] projects = model.getProjectsByTeamMember(viewHandler.getCurrentTeamMember());
    for (Project project:projects){
      projectList.add(new TeamMDetailsViewModel(project,
          viewHandler.getCurrentTeamMember(), model));
    }
  }

  public ObservableList<TeamMDetailsViewModel> getTeamList()
  {
    return projectList;
  }
}
