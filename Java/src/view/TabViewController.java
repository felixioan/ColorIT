package view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import model.ProjectManagementModel;

public class TabViewController {
  @FXML private TabPane tabPane;
  @FXML private ProjectTabViewController projectTabViewController;
  @FXML private TeamMemberViewController teamMemberViewController;

  private Region root;
  private ProjectManagementModel model;
  private ViewHandler viewHandler;

  public TabViewController(){
    //nothing
  }

  public void init(ViewHandler viewHandler, ProjectManagementModel model, Region root){
    this.root = root;
    projectTabViewController.init(viewHandler, model, root);
    teamMemberViewController.init(viewHandler, model, root);
  }

  public void reset(){
    //nothing so far
    projectTabViewController.reset();
    teamMemberViewController.reset();
  }

  public void openTab(int index){
    tabPane.getSelectionModel().select(index);
  }

  public Region getRoot()
  {
    return root;
  }

  public void openProjectWindow(String id){
    projectTabViewController.showView(id);
  }

  @FXML public void tabSelected(Event event)
  {
    if (teamMemberViewController != null && teamMemberViewController.getRoot() != null){
      int index = ((Tab) event.getSource()).getTabPane().getSelectionModel().getSelectedIndex();
      switch (index)
      {
        case 0:projectTabViewController.reset(); break;
        case 1:teamMemberViewController.reset();
      }
    }
  }
}
