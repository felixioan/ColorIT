package view;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.ProjectManagementModel;
import model.TeamMember;

public class ManageMembersViewController {
  public TextField name;
  public TextField email;
  public Label errorLabel;
  private TeamMember teamMember;

  private Region root;
  private ViewHandler viewHandler;
  private ProjectManagementModel model;

  public ManageMembersViewController(){
    //nothing
  }

  public void init(ViewHandler viewHandler,ProjectManagementModel model,Region root,boolean edit){
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    reset(edit);
  }

  public void reset(boolean edit){
    if(edit){
      teamMember = viewHandler.getCurrentTeamMember();
      this.root.setUserData("Edit team member");
      this.name.setText(teamMember.getName());
      this.email.setText(teamMember.getEmail());
    } else {
      teamMember = null;
      this.root.setUserData("Create Team member");
      this.name.setText("");
      this.name.setPromptText("Enter member name here.");
      this.email.setText("");
      this.email.setPromptText("Enter email address here.");
    }
  }

  public Region getRoot()
  {
    return root;
  }

  public void save()
  {
    errorLabel.setText("");
    if (teamMember != null){
      try {
        if(!this.name.getText().equals(this.teamMember.getName())){
          model.setName(teamMember,this.name.getText());
        }
        if(!this.email.getText().equals(this.teamMember.getEmail())){
          model.setEmail(teamMember,this.email.getText());
        }
        viewHandler.openView("TeamMemberView");
      } catch (Exception e) {
        errorLabel.setText(e.getMessage());
      }
    } else {
      String newName = this.name.getText();
      String newEmail = this.email.getText();
      try {
        model.addTeamMember(newName,newEmail);
        viewHandler.openView("TeamMemberView");
      } catch (Exception e){
        errorLabel.setText(e.getMessage());
      }
    }
  }

  public void cancel(ActionEvent actionEvent)
  {
    viewHandler.openView("TeamMemberView");
  }
}
