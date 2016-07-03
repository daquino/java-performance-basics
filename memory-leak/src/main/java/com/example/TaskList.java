package com.example;

import java.util.Deque;
import java.util.ArrayDeque;

public class TaskList {
    private Deque<Task> tasks = new ArrayDeque<>();

    public void addTaskWithMemoryLeak(final Task task) {
        tasks.add(task);
        tasks.peekLast().execute();
    }

    public void addTask(final Task task) {
        tasks.add(task);
        tasks.poll().execute();
    }
}
