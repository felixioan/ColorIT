package persistence;

import model.ProjectList;
import model.TeamMemberList;
import java.io.*;

public class FileHandling
{
    private TeamMemberList teamMemberList;
    private ProjectList projectList;

    public TeamMemberList loadTeamMembersFromFile() {
        teamMemberList = new TeamMemberList();
        String filename = "./Java/TeamMemberList.bin";
        ObjectInputStream in = null;

        try
        {
            File file = new File(filename);
            FileInputStream fis = new FileInputStream(file);
            in = new ObjectInputStream(fis);

            try
            {
                teamMemberList = (TeamMemberList) in.readObject();
            }
            catch (InvalidClassException e)
            {
                System.out.print("\nThe TeamMemberList.bin contains an outdated class. You need to delete the .bin data\n" +
                        "run the program again and fill the list from the start.\n" +
                        "Alternatively revert the changes in the model classes.\n");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch (EOFException e)
        {
            System.out.print("The file is empty");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                in.close();
            }
            catch (NullPointerException e)
            {
                System.out.println(", error while closing, because the file is empty.");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("reading from : " + filename +
                " complete");
        return teamMemberList;
    }

    public ProjectList loadProjectsFromFile() {
        projectList = new ProjectList();
        String filename = "./Java/ProjectList.bin";
        ObjectInputStream in = null;

        try
        {
            File file = new File(filename);
            FileInputStream fis = new FileInputStream(file);
            in = new ObjectInputStream(fis);

            try 
            {
                projectList = (ProjectList) in.readObject();    
            }
            catch (InvalidClassException e)
            {
                System.out.print("\nThe ProjectList.bin contains an outdated class. You need to delete the .bin data\n" +
                        "run the program again and fill the list from the start.\n" +
                        "Alternatively revert the changes in the model classes.\n");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch (EOFException e)
        {
            System.out.print("The file is empty");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                in.close();
            }
            catch (NullPointerException e)
            {
                System.out.println(", error while closing, because the file is empty.");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("reading from : " + filename +
                " complete");
        return projectList;
    }

    public void saveTeamMemberListToFile(TeamMemberList teamMemberList)
    {
        String filename = "./Java/TeamMemberList.bin";

        ObjectOutputStream out = null;

        try {
            File file = new File(filename);
            FileOutputStream fos = new FileOutputStream(file);
            out = new ObjectOutputStream(fos);

                out.writeObject(teamMemberList);
        }
        catch (IOException e)
        {
            System.out.println("did not save to - " + filename);
        }
        finally
        {
            try
            {
                out.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("saving to : " + filename +
                " complete");
    }

    public void saveProjectListToFile(ProjectList projectList)
    {
        String filename = "./Java/ProjectList.bin";

        ObjectOutputStream out = null;

        try {
            File file = new File(filename);
            FileOutputStream fos = new FileOutputStream(file);
            out = new ObjectOutputStream(fos);

            out.writeObject(projectList);
        }
        catch (IOException e)
        {
            System.out.println("did not save to - " + filename);
        }
        finally
        {
            try
            {
                out.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("saving to : " + filename +
                " complete");

    }




    
    
}
