package model;

public interface ProjectManagementModel
{

    void addProject(String name, String description);
    Project[] getProjectsByName(String projectName);
    Project[] getAllProjects();
    void deleteProject(int projectID);
    Project[] getProjectsByScrumMaster(TeamMember teamMember);
    Project[] getProjectsByProductOwner(TeamMember teamMember);
    Project[] getProjectsByTeamMember(TeamMember teamMember);
    int getProjectID(Project project);
    String getProjectName(Project project);
    String getProjectDescription(Project project);
    TeamMember[] getTeamMembers(Project project);
    TeamMember getScrumMaster(Project project);
    TeamMember getProductOwner(Project project);
    void addTeamMember(Project project, TeamMember teamMember);
    void removeTeamMember(Project project, TeamMember teamMember);
    void setName(Project project, String name) throws Exception;
    void setScrumMaster(Project project, TeamMember teamMember);
    void setProductOwner(Project project, TeamMember teamMember);
    float getProductivityOfMember(TeamMember teamMember) throws Exception;
    void setDescription(Project project, String description);
    Requirement[] getRequirementsByStatus(Project project, String status);
    void deleteRequirement(Project project, Requirement requirement);
    void reorderRequirements(Project project, int index1, int index2);
    void addRequirement(Project project, String name, String nonFunctionalDescription,
                               long deadline, TeamMember responsibleTeamMember);
    void addRequirement(Project project, String name, String[] FunctionalDescription,
                               long deadline, TeamMember responsibleTeamMember);
    Requirement[] getAllRequirements(Project project);
    Requirement[] getRequirementsBeforeDeadline(Project project, int days);
    Requirement[] getRequirementsByResponsibleTeamMember(Project project, TeamMember responsible);
    Requirement getRequirementByID(Project project, int requirementID);
    Requirement[] getRequirementsByName(Project project, String name);
    int getRequirementID(Requirement requirement);
    int getUsedTime(Requirement requirement);
    String getName(Requirement requirement);
    boolean isFunctional(Requirement requirement);
    String[] getDescription(Requirement requirement);
    TaskList getTasks(Requirement requirement);
    int getEstimatedTime(Requirement requirement);
    TeamMember getResponsibleTeamMember(Requirement requirement);
    String getStatus(Requirement requirement);
    long getDeadlineTime(Requirement requirement);
    void setName(Requirement requirement, String name);
    void setDescription(Requirement requirement, String description);
    void setDescription(Requirement requirement, String who, String what, String why);
    void setDeadlineTime(Requirement requirement, long newTime);
    void setResponsibleTeamMember(Requirement requirement, TeamMember teamMember);
    void setStatus(Requirement requirement, String status);
    void addTask(Requirement requirement, String name, int id, int estimatedTime,
                        String description, long deadlineTime, TeamMember responsibleTeamMember);
    void addTask(Requirement requirement, String name, int id, int estimatedTime,
                        String description, long deadlineTime);
    void ChangeTaskTrackTime(Task task, TeamMember teamMember, int newTime);
    Task[] getAllTasks(Requirement requirement);
    Task[] getTasksByName(Requirement requirement, String name);
    void deleteTask(Requirement requirement, Task task);
    void changeTask(Task task, String name, int estimatedTime, String description,
                           long deadlineTime, TeamMember responsibleTeamMember);
    Task[] getTasksByStatus(Requirement requirement, String status);
    Task[] getTasksDaysBeforeDeadline(Requirement requirement, int days);
    int getTaskID(Task task);
    String getName(Task task);
    int getRequirementID(Task task);
    String getDescription(Task task);
    int getEstimatedTime(Task task);
    long getDeadlineTime(Task task);
    int getTimeSpend(Task task);
    int getTimeSpendOfMember(Task task, TeamMember teamMember);
    TeamMember[] getTeamMembers(Task task);
    TeamMember getResponsibleTeamMember(Task task);
    String getStatus(Task task);
    void setName(Task task, String name);
    void setDescription(Task task, String description);
    void setEstimatedTime(Task task, int estimatedTime);
    void setDeadlineTIme(Task task, long time);
    void setResponsibleTeamMember(Task task, TeamMember teamMember);
    void setStatus(Task task, String status);
    void setTimeWorked(Task task, TeamMember teamMember, int time);
    void addTeamMember(Task task, TeamMember teamMember);
    void addTeamMember(String name, String email);
    void deleteTeamMember(TeamMember teamMember);
    TrackTime[] getTrackTime(Task task);
    int getTotalTime(Task task);
    TeamMember[] getAllTeamMembers();
    String getEmail(TeamMember teamMember);
    String getName(TeamMember teamMember);
    void setEmail(TeamMember teamMember, String email);
    void setName(TeamMember teamMember, String name);
    Project[] getProjectsWorkedOn(TeamMember teamMember);
}
