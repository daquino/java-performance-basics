package com.example;

public class Task {
    private Object[] data = new Object[1000];
    private String name;

    public Task() {
        this.name = String.format("Task %d", (System.currentTimeMillis() / 1000));
    }
    public Task(final String name) {
        this.name = name;
    }
    public void execute() {
        System.out.println(String.format("Executing %s", name));
    }
}
