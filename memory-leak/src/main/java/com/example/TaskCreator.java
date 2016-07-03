package com.example;

public class TaskCreator {
    private TaskList taskList;

    public TaskCreator(final TaskList taskList) {
        this.taskList = taskList;
    }

    public void createTask() {
        taskList.addTask(new Task());
    }

    public void createTask(final String name) {
        taskList.addTask(new Task(name));
    }
}
