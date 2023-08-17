package ToDoClass;

public class ToDoItem {
    private int id; //id-or-ticket number of the task
    private String taskName;
    private String taskDescription; //task at hand
    private boolean status; //true-completed false-not completed

    //Constructor
    public ToDoItem(int id, String taskName,String taskDescription, boolean status) {
        this.id = id;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = status;

    }
    public ToDoItem(){
        //Blank
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + " "
                + taskName + " "
                + taskDescription + " "
                + (status ? "completed" : "not completed");
    }
}
