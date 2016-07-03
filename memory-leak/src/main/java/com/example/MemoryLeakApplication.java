package com.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.geometry.Orientation;

public class MemoryLeakApplication extends Application {
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Memory Leak");
        Button button = new Button();
        button.setText("Start tasks with memory leak");
        button.setOnAction( (event) -> {
            System.out.println("Starting tasks with memory leak");
            TaskList taskList = new TaskList();
            final TaskCreator creator = new TaskCreator(taskList);
            new Thread(() -> {
                for(int i=0; i < 10000; i++) {
                    creator.createMemoryLeakingTask(String.format("Leaky Task %d", i));
                }
            }).start();
        });
        Button goodButton = new Button();
        goodButton.setText("Start tasks");
        goodButton.setOnAction( (event) -> {
            System.out.println("Starting tasks");
            TaskList taskList = new TaskList();
            final TaskCreator creator = new TaskCreator(taskList);
            new Thread(() -> {
                for(int i=0; i < 10000; i++) {
                    creator.createTask(String.format("Good Task %d", i));
                }
            }).start();
        });
        FlowPane root = new FlowPane();
        root.setVgap(8);
        root.setHgap(4);
        root.setPrefWrapLength(300);
        root.setOrientation(Orientation.VERTICAL);
        root.getChildren().add(button);
        root.getChildren().add(goodButton);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

}
