package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Project;
import model.ProjectManagementModel;
import model.TeamMember;

public class ProjecTeamListViewModel
{
  private ObservableList<ProjectTeamViewModel> teamList;
  private ProjectManagementModel model;
  private Project currentProject;

  public ProjecTeamListViewModel(ProjectManagementModel model, Project currentProject){
    this.model = model;
    this.currentProject = currentProject;
    this.teamList = FXCollections.observableArrayList();

    update();
  }

  public void update(){
    if (currentProject == null){
      return;
    }
    this.teamList.clear();
    TeamMember[] teamMembers = model.getTeamMembers(currentProject);
    for (TeamMember teamMember:teamMembers){
      teamList.add(new ProjectTeamViewModel(teamMember,currentProject,model));
    }
  }

  public ObservableList<ProjectTeamViewModel> getTeamList()
  {
    return teamList;
  }

  public void remove(TeamMember teamMember){
    int size = teamList.size();
    for (int i = 0;i < size;i++){
      if (teamList.get(i).getNameProperty().get().equals(teamMember.getName())){
        teamList.remove(i);
        break;
      }
    }
  }
}
