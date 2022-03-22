import javafx.application.Application;
import javafx.stage.Stage;
import model.ProjectManagementModel;
import model.ProjectManagementModelManager;
import view.ViewHandler;

public class MyApplication extends Application
{
    public void start(Stage primaryStage)
    {
        ProjectManagementModel model = new ProjectManagementModelManager();
        ViewHandler view = new ViewHandler(model);
        view.start(primaryStage);
    }
}
