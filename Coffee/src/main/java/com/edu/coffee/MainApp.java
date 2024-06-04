package com.edu.coffee;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * The MainApp class is the entry point of the application.
 * It initializes the JavaFX application and loads the main FXML layout.
 */

public class MainApp extends Application {

    /**
     * The start method initializes the JavaFX application and sets up the primary stage.
     * It loads the main FXML layout, sets the stage title, size, and scene.
     *
     * @param primaryStage The primary stage of the JavaFX application.
     * @throws Exception If an error occurs during loading the FXML layout.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Завантажуємо головний макет FXML
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        // Налаштовуємо головний етап
        primaryStage.setTitle("Соffee"); // Встановлюємо назву
        primaryStage.setResizable(false); // Вимикаємо можливість зміни розміру
        primaryStage.setWidth(1280); // Встановлюємо ширину
        primaryStage.setHeight(720); // Встановлюємо висоту
        primaryStage.setScene(new Scene(root)); // Встановлюємо сцену
        primaryStage.show(); // Показуємо головний етап
    }

    /**
     * The main method is the entry point of the application.
     * It launches the JavaFX application.
     *
     * @param args The command line arguments.
     */

    public static void main(String[] args) {
        launch(args); // Запускаємо додаток JavaFX
    }
}
