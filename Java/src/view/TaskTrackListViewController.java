package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Project;
import model.ProjectManagementModel;
import model.Task;
import model.TeamMember;

public class TaskTrackListViewController
{
  private ObservableList<TaskTrackViewModel> teamList;
  private ProjectManagementModel model;
  private Task currentTask;

  public TaskTrackListViewController(ProjectManagementModel model, Task currentTask){
    this.model = model;
    this.currentTask = currentTask;
    this.teamList = FXCollections.observableArrayList();

    update();
  }

  public void update(){
    if (currentTask == null){
      return;
    }
    this.teamList.clear();
    TeamMember[] teamMembers = model.getTeamMembers(currentTask);
    for (TeamMember teamMember:teamMembers){
      teamList.add(new TaskTrackViewModel(teamMember,currentTask,model));
    }
  }

  public ObservableList<TaskTrackViewModel> getTeamList()
  {
    return teamList;
  }

  public void remove(TeamMember teamMember){
    int size = teamList.size();
    for (int i = 0;i < size;i++){
      if (teamList.get(i).getNameProperty().equals(teamMember.getName()) &&
      teamList.get(i).getEmail().equals(model.getEmail(teamMember))){
        teamList.remove(i);
        break;
      }
    }
  }
}
