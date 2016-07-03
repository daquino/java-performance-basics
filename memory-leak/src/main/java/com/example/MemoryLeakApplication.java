package com.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MemoryLeakApplication extends Application {
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Memory Leak");
        Button button = new Button();
        button.setText("Start Memory Leak");
        button.setOnAction( (event) -> {
            System.out.println("Starting memory leak");
            TaskList taskList = new TaskList();
            final TaskCreator creator = new TaskCreator(taskList);
            new Thread(() -> {
                for(int i=0; i < 10000; i++) {
                    creator.createTask(String.format("Task %d", i));
                }
            }).start();
        });
        StackPane root = new StackPane();
        root.getChildren().add(button);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

}
