package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.ProjectManagementModel;
import model.TeamMember;
import model.TeamMemberList;

import java.util.Optional;

public class TeamMemberViewController {
  @FXML private TableView<TeamMemberViewModel> teamMemberList;
  @FXML private TableColumn<TeamMemberViewModel, String> nameColumn;
  @FXML private TableColumn<TeamMemberViewModel, String> emailColumn;
  private Region root;
  private ProjectManagementModel model;
  private ViewHandler viewHandler;
  private TeamMemberListViewModel viewModel;

  public TeamMemberViewController(){
    //nothing
  }

  public void init(ViewHandler viewHandler, ProjectManagementModel model, Region root){
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

  public void reset()
  {
    viewModel.update();
  }

  public void deleteTeamMember(ActionEvent actionEvent)
  {
    try {

      TeamMemberViewModel selectedMember = teamMemberList.getSelectionModel().getSelectedItem();
      TeamMember teamMember = new TeamMember(selectedMember.getNameProperty().get(),
                                              selectedMember.getEmailProperty().get());

      model.deleteTeamMember(teamMember);
      viewModel.remove(teamMember);
      teamMemberList.getSelectionModel().clearSelection();
    } catch (Exception e){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("error");
      alert.setHeaderText("grade do not found");
      /*Optional<ButtonType> result = */alert.show();
      //boolean hej = (result.isPresent())&&(result.get()== ButtonType.OK);
    }
    viewModel.update();
  }

  public void editTeamMember(ActionEvent actionEvent)
  {
    TeamMemberViewModel selectedMember = teamMemberList.getSelectionModel().getSelectedItem();
    TeamMember[] teamMembers = model.getAllTeamMembers();
    TeamMember currentTeamMember = null;

    System.out.println(teamMemberList.getSelectionModel().getFocusedIndex());

    if (selectedMember == null){
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("select a team member");
      alert.setHeaderText("You need to select team member that you want to edit first.");
      alert.show();
    } else {
      for (TeamMember teamMember: teamMembers){
        if (teamMember.getEmail().equals(selectedMember.getEmailProperty().get()) &&
            teamMember.getName().equals(selectedMember.getNameProperty().get())){
          currentTeamMember = teamMember;
        }
      }
      viewHandler.setCurrentTeamMember(currentTeamMember);
      viewHandler.openView("ManageMember");
    }
  }

  public void addTeamMember(ActionEvent actionEvent)
  {
    viewHandler.openView("AddMember");
  }

  public void getMoreInfo()
  {
    viewHandler.setCurrentTeamMember(model.getAllTeamMembers()[teamMemberList.getSelectionModel().getFocusedIndex()]);
    viewHandler.openView("TeamMemberDetails");
  }
}
