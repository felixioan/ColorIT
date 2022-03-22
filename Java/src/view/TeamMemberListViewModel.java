package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ProjectManagementModel;
import model.TeamMember;

public class TeamMemberListViewModel
{
  private ObservableList<TeamMemberViewModel> teamList;
  private ProjectManagementModel model;

  public TeamMemberListViewModel(ProjectManagementModel model){
    this.model = model;
    this.teamList = FXCollections.observableArrayList();
    update();
  }

  public void update(){
    this.teamList.clear();
    TeamMember[] teamMembers = model.getAllTeamMembers();
    for (TeamMember teamMember:teamMembers){
      teamList.add(new TeamMemberViewModel(teamMember));
    }
  }

  public ObservableList<TeamMemberViewModel> getTeamList()
  {
    return teamList;
  }

  public void remove(TeamMember teamMember){
    int size = teamList.size();
    for (int i = 0;i < size;i++){
      if (teamList.get(i).getEmailProperty().equals(teamMember.getEmail()) &&
          teamList.get(i).getNameProperty().equals(teamMember.getName())){
        teamList.remove(i);
        break;
      }
    }
  }
}
