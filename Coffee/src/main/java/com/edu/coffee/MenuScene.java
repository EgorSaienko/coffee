package com.edu.coffee;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;

import java.util.List;

/**
 * The MenuScene class is responsible for setting up the scene displaying the menu with meals and drinks.
 */

public class MenuScene {
    /**
     * The setupMenuScene method sets up the scene displaying meals and drinks.
     *
     * @param messageLabelLeft  the label where the meals are displayed
     * @param messageLabelRight the label where the drinks are displayed
     */

    public static void setupMenuScene(Label messageLabelLeft, Label messageLabelRight) {
        // Створення VBox для відображення обідів
        VBox mealBox = new VBox();
        // Додавання мітки для відображення заголовка "Обіди:"
        Label viewLabel = new Label("Обіди:\n");
        viewLabel.getStyleClass().add("view-label");
        mealBox.getChildren().add(viewLabel);
        // Отримання списку обідів з класу Item
        List<Item> meals = Item.readMeals();
        // Додавання інформації про обіди до VBox
        for (Item meal : meals) {
            Label nameLabel = new Label(meal.getName());
            nameLabel.getStyleClass().add("meal-name");
            // Розділення та форматування страв
            String dishes = meal.getDishes();
            String[] dishesArray = dishes.split(", ");
            StringBuilder formattedDishes = new StringBuilder();
            for (String dish : dishesArray) {
                formattedDishes.append(dish).append("\n");
            }
            Label dishesLabel = new Label(formattedDishes.toString());
            dishesLabel.getStyleClass().add("meal-description");
            Label priceLabel = new Label("Ціна: " + meal.getPrice());
            priceLabel.getStyleClass().add("meal-price");
            // Створення кнопки з зображенням для купівлі
            Button buyButton = new Button();
            // Видаляє всі класи стилів, що застосовуються до кнопки
            buyButton.getStyleClass().clear();
            // шлях до  зображення
            Image buyImage = new Image(MenuScene.class.getResource("/com/edu/coffee/img/buy.png").toExternalForm());
            ImageView buyImageView = new ImageView(buyImage);
            // встановлення ширини зображення
            buyImageView.setFitWidth(42);
            // встановлення висоти зображення
            buyImageView.setFitHeight(42);
            buyButton.setGraphic(buyImageView);
            // Додавання стилів до кнопки з зображенням
            buyButton.getStyleClass().add("buy-button");
            // Додавання дій для кнопки
            buyButton.setOnAction(event -> {
                OrdersScene.addItemToOrder(meal);
                // Дії при натисканні кнопки купівлі
                System.out.println("Куплено: " + meal.getName());
            });

            VBox mealInfo = new VBox(nameLabel, dishesLabel, priceLabel, buyButton);
            mealInfo.getStyleClass().add("meal-info");
            mealBox.getChildren().add(mealInfo);
        }
        // Обгортання VBox з обідами у ScrollPane
        ScrollPane mealScrollPane = new ScrollPane(mealBox);
        // Додаємо CSS клас до ScrollPane
        mealScrollPane.getStyleClass().add("transparent-scroll-pane");
        // Автоматично підганяти ширину під вміст
        mealScrollPane.setFitToWidth(true);
        messageLabelLeft.setGraphic(mealScrollPane);

        // Створення VBox для відображення напоїв
        VBox drinkBox = new VBox();
        // Додавання мітки для відображення заголовка "Напої:"
        Label viewLabelDrinks = new Label("Напої:\n");
        viewLabelDrinks.getStyleClass().add("view-label");
        drinkBox.getChildren().add(viewLabelDrinks);
        // Отримання списку напоїв з класу Item
        List<Item> drinks = Item.readDrinks();
        // Додавання інформації про напоїв до VBox
        for (Item drink : drinks) {
            Label nameLabel = new Label(drink.getName());
            nameLabel.getStyleClass().add("drink-name");
            // Розділення та форматування напоїв
            String dishes = drink.getDishes();
            String[] dishesArray = dishes.split(", ");
            StringBuilder formattedDishes = new StringBuilder();
            for (String dish : dishesArray) {
                formattedDishes.append(dish).append("\n");
            }
            Label dishesLabel = new Label(formattedDishes.toString());
            dishesLabel.getStyleClass().add("drink-description");
            Label priceLabel = new Label("Ціна: " + drink.getPrice());
            priceLabel.getStyleClass().add("drink-price");
            // Створення кнопки з зображенням для купівлі
            Button buyButton = new Button();
            // Видаляє всі класи стилів, що застосовуються до кнопки
            buyButton.getStyleClass().clear();
            // шлях до  зображення
            Image buyImage = new Image(MenuScene.class.getResource("/com/edu/coffee/img/buy.png").toExternalForm());
            ImageView buyImageView = new ImageView(buyImage);
            // встановлення ширини зображення
            buyImageView.setFitWidth(42);
            // встановлення висоти зображення
            buyImageView.setFitHeight(42);
            buyButton.setGraphic(buyImageView);
            // Додавання стилів до кнопки з зображенням
            buyButton.getStyleClass().add("buy-button");
            // Додавання дій для кнопки
            buyButton.setOnAction(event -> {
                OrdersScene.addItemToOrder(drink);
                // Дії при натисканні кнопки купівлі
                System.out.println("Куплено: " + drink.getName());
            });
            VBox drinkInfo = new VBox(nameLabel, dishesLabel, priceLabel, buyButton);
            drinkInfo.getStyleClass().add("drink-info");
            drinkBox.getChildren().add(drinkInfo);
        }
        // Обгортання VBox з напоями у ScrollPane
        ScrollPane drinkScrollPane = new ScrollPane(drinkBox);
        // Автоматично підганяти ширину під вміст
        drinkScrollPane.setFitToWidth(true);
        // Додаємо CSS клас до ScrollPane
        drinkScrollPane.getStyleClass().add("transparent-scroll-pane");
        messageLabelRight.setGraphic(drinkScrollPane);
    }
}
