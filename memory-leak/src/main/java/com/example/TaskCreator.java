package com.example;

public class TaskCreator {
    private TaskList taskList;

    public TaskCreator(final TaskList taskList) {
        this.taskList = taskList;
    }

    public void createTask() {
        taskList.addTask(new Task());
    }

    public void createMemoryLeakingTask(final String name) {
        taskList.addTaskWithMemoryLeak(new Task(name));
    }

    public void createTask(final String name) {
        taskList.addTask(new Task(name));
    }
}
