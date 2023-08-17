package ToDoClass;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;

@RestController
public class ToDoController {
    HashMap<String, ToDoItem> toDoList = new  HashMap<>();
    HashMap<String, ToDoItem> completedTasks = new  HashMap<>();

    private void update(){
        for(String s : toDoList.keySet()){
            if(toDoList.get(s).getStatus()){
                completedTasks.put(s, toDoList.get(s));
                toDoList.remove(s);
            }
        }
    }
    //Trying to implement all of
    //CRUDL Operations (create/read/update/delete/list)
    // use POST (Create), GET(Read), PUT(Update), DELETE(Delete), GET(List) methods for CRUDL

    //LIST - GET
    @GetMapping("/tasks")
    public HashMap<String,ToDoItem> getAllTasks() {
        return toDoList;
    }

    //LIST (all completed tasks) - GET
    @GetMapping("/tasks/completed")
    public HashMap<String,ToDoItem> getCompletedTasks() {
        return completedTasks;
    }

    //CREATE - POST
    @PostMapping("/tasks")
    public @ResponseBody String createTask(@RequestBody ToDoItem toDoItem) {
        System.out.println(toDoItem);
        toDoList.put(toDoItem.getTaskName(), toDoItem);
        update();
        if(completedTasks.containsKey(toDoItem.getTaskName())){return "Wow, you already finished your task";}
        return "New Task: "+ toDoItem.getTaskName() + " Successfully Added";
    }

    //READ - GET
    @GetMapping("/tasks/{taskName}")
    public @ResponseBody ToDoItem getTask(@PathVariable String taskName) {
        ToDoItem t = toDoList.get(taskName);
        return t;
    }

    //UPDATE - PUT
    @PutMapping("/tasks/{taskName}/{boo}")
    public @ResponseBody ToDoItem updateTask(@PathVariable String taskName, @PathVariable boolean boo) {
        ToDoItem t = toDoList.get(taskName);
        t.setStatus(boo);
        toDoList.put(taskName, t);
        //toDoList.replace(taskName, toDoList.get(taskName));
        update();
        if(completedTasks.containsKey(taskName)){
            System.out.println("Wow, you finished your task");
            return null;
        }
        return toDoList.get(taskName);
    }

    //DELETE - DELETE
    @DeleteMapping("/tasks/{taskName}")
    public @ResponseBody HashMap<String, ToDoItem> deleteTask(@PathVariable String taskName) {
        toDoList.remove(taskName);
        return toDoList;
    }

    @DeleteMapping("/tasks/completed/{taskName}")
    public @ResponseBody HashMap<String, ToDoItem> deleteCompletedTask(@PathVariable String taskName) {
        completedTasks.remove(taskName);
        return completedTasks;
    }

    //BODY
    /**
     * {
     *     "id" : 1,
     *     "taskName" : "Push to Git",
     *     "taskDescription" : "Push files to be shown for demo1 into git under Experiments/KAUSSHIK_MANOJKUMAR/",
     *     "status" : false
     *
     * }
     */
}
