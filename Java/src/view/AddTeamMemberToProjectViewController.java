package view;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import model.ProjectManagementModel;
import model.TeamMember;

public class AddTeamMemberToProjectViewController
{
  public TableView<TeamMemberViewModel> teamMemberList;
  public TableColumn<TeamMemberViewModel, String> nameColumn;
  public TableColumn<TeamMemberViewModel, String> emailColumn;

  private Region root;
  private ProjectManagementModel model;
  private ViewHandler viewHandler;
  private TeamMemberListViewModel viewModel;
  private boolean addToProject;

  public AddTeamMemberToProjectViewController(){
    //nothing
  }

  public void init(ViewHandler viewHandler, ProjectManagementModel model, Region root, boolean addToProject){
    this.addToProject = addToProject;
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    this.viewModel = new TeamMemberListViewModel(model);

    nameColumn.setCellValueFactory(
        cellDate -> cellDate.getValue().getNameProperty()
    );
    emailColumn.setCellValueFactory(
        cellData -> cellData.getValue().getEmailProperty()
    );

    teamMemberList.setItems(viewModel.getTeamList());
  }

  public Region getRoot()
  {
    return root;
  }

  public void reset(boolean addToProject)
  {
    this.addToProject = addToProject;
    viewModel.update();
  }

  public void addTeamMember()
  {
    TeamMemberViewModel selectedMember = teamMemberList.getSelectionModel().getSelectedItem();
    TeamMember[] teamMembers = model.getAllTeamMembers();
    TeamMember currentTeamMember = null;

    if (selectedMember ==null){
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("select a team member");
      alert.setHeaderText("You need to select team member that you want to add first.");
      alert.show();
    } else {
      for (TeamMember teamMember: teamMembers){
        if (teamMember.getEmail().equals(selectedMember.getEmailProperty().get()) &&
            teamMember.getName().equals(selectedMember.getNameProperty().get())){
          currentTeamMember = teamMember; break;
        }
      }
      if (addToProject){
        model.addTeamMember(viewHandler.getCurrentProject(),currentTeamMember);
        viewHandler.openView("RequirementView");
      } else {
        model.addTeamMember(viewHandler.getCurrentTask(),currentTeamMember);
        viewHandler.openView("TaskView");
      }
    }

  }

  public void close(ActionEvent actionEvent)
  {
    if (addToProject){
      viewHandler.openView("RequirementView");
    } else {
      viewHandler.openView("TaskView");
    }
  }
}
