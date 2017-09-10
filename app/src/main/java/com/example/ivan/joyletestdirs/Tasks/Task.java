package com.example.ivan.joyletestdirs.Tasks;

/**
 * Created by Ivan on 07.09.2017.
 */

public class Task {
    private String taskName;

    public Task(){
        taskName = "";
    }

    public Task(String taskName){
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
