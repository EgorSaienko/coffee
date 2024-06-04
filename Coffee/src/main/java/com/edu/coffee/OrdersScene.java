package com.edu.coffee;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The OrdersScene class handles the display and management of orders in the coffee shop application.
 * It provides methods to add, remove, and display items in the order list, as well as to set up scenes
 * for viewing and processing orders.
 */
public class OrdersScene {
    private static List<Item> orderedItems = new ArrayList<>();

    // Додає елемент до списку замовлень
    public static void addItemToOrder(Item item) {
        orderedItems.add(item);
    }

    // Повертає список замовлених елементів
    public static List<Item> getOrderedItems() {
        return orderedItems;
    }

    // Видаляє елемент зі списку замовлень
    public static void removeItemFromOrder(Item item) {
        orderedItems.remove(item);
    }

    // Налаштовує сцену замовлень
    public static void setupOrdersScene(VBox ordersBox) {
        ordersBox.getChildren().clear();
        // Додавання мітки для відображення заголовка "Корзина:"
        Label viewLabel = new Label("Корзина:\n");
        viewLabel.getStyleClass().add("view-label");
        ordersBox.getChildren().add(viewLabel);

        for (Item item : orderedItems) {
            // Створюємо мітку для відображення імені елемента
            Label itemLabel = new Label(item.getName());
            itemLabel.getStyleClass().add("meal-name");
            // Створюємо кнопку для видалення елемента зі списку
            Button removeButton = new Button("Видалити");
            removeButton.getStyleClass().add("remove-button"); // Додавання стилів до кнопки
            removeButton.setOnAction(event -> {
                // Видаляємо елемент зі списку та оновлюємо сцену
                removeItemFromOrder(item);
                setupOrdersScene(ordersBox); // Оновлення сцени після видалення
            });
            ordersBox.getChildren().addAll(itemLabel, removeButton);
        }
    }

    // Nалаштовує сцену для оформлення замовлень
    public static void setup2OrdersScene(VBox ordersBox) {
        long total = calculateTotal();
        ordersBox.getChildren().clear();

        // Створення полів для введення даних
        TextField waiterNameField = new TextField();
        waiterNameField.getStyleClass().add("transparent-text-field");
        waiterNameField.setPromptText("Офіціант:");
        TextField tableNumberField = new TextField();
        tableNumberField.getStyleClass().add("transparent-text-field");
        tableNumberField.setPromptText("Стіл:");
        TextField paymentTypeField = new TextField();
        paymentTypeField.getStyleClass().add("transparent-text-field");
        paymentTypeField.setPromptText("Спосіб оплати:");

        // Додавання мітки для відображення загальної суми
        Label totalLabel = new Label("Загальна сума: " + total + " грн");
        totalLabel.getStyleClass().add("total-label");
        ordersBox.getChildren().add(totalLabel);

        // Створення кнопки для оформлення замовлення
        Button addOrderButton = new Button("Оформити");
        addOrderButton.getStyleClass().add("addOrderButton");
        addOrderButton.setOnAction(event -> {
            // Отримання даних з полів введення
            String name = waiterNameField.getText();
            String number = tableNumberField.getText();
            String type = paymentTypeField.getText();
            LocalTime currentTime = LocalTime.now();
            Order order = new Order(currentTime, null, name, number, type, total, null);

            for (Item item : orderedItems) {
                order.addNewItem(item);
            }
            String list = order.getImpotantList();

            Order neworder = new Order(currentTime, null, name, number, type, total, list);
            Order.addOrder(neworder);

            // Створення нового вікна для повідомлення
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setTitle("Замовлення оформлене");
            // Додавання мітки з повідомленням
            Label messageLabel = new Label("Ок");
            StackPane layout = new StackPane(messageLabel);
            Scene scene = new Scene(layout, 300, 100);
            dialogStage.setScene(scene);

            // Показуємо спливаюче вікно
            dialogStage.show();

            // Створення таймера для закриття вікна через 2 секунди
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(dialogStage::close);
                }
            }, 2000);
        });

        // Додавання всіх елементів до VBox
        ordersBox.getChildren().addAll(
                waiterNameField, tableNumberField, paymentTypeField, addOrderButton
        );
    }

    // Розраховує загальну суму замовлення
    private static long calculateTotal() {
        long total = 0;
        for (Item item : orderedItems) {
            total += item.getPrice();
        }
        return total;
    }
}