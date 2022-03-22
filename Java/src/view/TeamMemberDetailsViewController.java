package view;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import model.ProjectManagementModel;
import model.TeamMember;

public class TeamMemberDetailsViewController {
  public Label teamMemberName;
  public Label teamMemberEmail;
  public Label teamMemberProductivity;
  public TableView<TeamMDetailsViewModel> projectList;
  public TableColumn<TeamMDetailsViewModel, String> projectName;
  public TableColumn<TeamMDetailsViewModel, String> memberRole;

  private Region root;
  private ViewHandler viewHandler;
  private ProjectManagementModel model;
  private TeamMember currentTeamMember;
  private TeamMDetailsListViewModel viewModel;

  public TeamMemberDetailsViewController(){
    //nothing
  }

  public void init(ViewHandler viewHandler,ProjectManagementModel model, Region root){
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    reset();
  }

  public void reset(){
    currentTeamMember = viewHandler.getCurrentTeamMember();
    if (currentTeamMember != null){
      this.teamMemberName.setText(model.getName(currentTeamMember));
      this.teamMemberEmail.setText(model.getEmail(currentTeamMember));

      try
      {
        this.teamMemberProductivity.setText(String.valueOf(model.getProductivityOfMember(currentTeamMember)));
      }
      catch (Exception e)
      {
        this.teamMemberProductivity.setText(e.getMessage());
      }

      this.viewModel = new TeamMDetailsListViewModel(model,viewHandler);

      projectName.setCellValueFactory(
          cellDate -> cellDate.getValue().getProjectName()
      );
      memberRole.setCellValueFactory(
          cellData -> cellData.getValue().getRoleName()
      );

      projectList.setItems(viewModel.getTeamList());
    }
  }

  public Region getRoot()
  {
    return root;
  }

  public void openProject()
  {
    viewHandler.setCurrentProject(model.
        getProjectsByTeamMember(viewHandler.
            getCurrentTeamMember())[projectList.getSelectionModel().getSelectedIndex()]);
    viewHandler.openView("RequirementView");
  }

  public void back()
  {
    viewHandler.openView("TeamMemberView");
  }
}
