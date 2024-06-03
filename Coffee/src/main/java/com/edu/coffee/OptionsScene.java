package com.edu.coffee;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

import static com.edu.coffee.Item.addMeal;
import static com.edu.coffee.Item.addDrink;
import static com.edu.coffee.Item.removeMeal;
import static com.edu.coffee.Item.removeDrink;

public class OptionsScene {

    public static void setupOptionsScene(VBox leftBox, VBox rightBox) {
        Label addMealLabel = new Label("Додати нову страву:");
        addMealLabel.getStyleClass().add("meal-name");
        TextField mealNameField = new TextField();
        mealNameField.getStyleClass().add("transparent-text-field");
        mealNameField.setPromptText("Назва страви");
        TextField mealPriceField = new TextField();
        mealPriceField.getStyleClass().add("transparent-text-field");
        mealPriceField.setPromptText("Ціна страви");
        TextField mealDishesField = new TextField();
        mealDishesField.getStyleClass().add("transparent-text-field");
        mealDishesField.setPromptText("Список страв (через кому)");

        Button addMealButton = new Button("Додати страву");
        addMealButton.getStyleClass().add("addOrderButton");
        addMealButton.setOnAction(event -> {
            String name = mealNameField.getText();
            long price = Long.parseLong(mealPriceField.getText());
            String dishes = mealDishesField.getText();
            Item newMeal = new Item(name, price, dishes);
            addMeal(newMeal);
            System.out.println("Додано нову страву: " + name);
            updateDeleteOptions(rightBox);
        });

        Label addDrinkLabel = new Label("Додати новий напій:");
        addDrinkLabel.getStyleClass().add("meal-name");
        TextField drinkNameField = new TextField();
        drinkNameField.getStyleClass().add("transparent-text-field");
        drinkNameField.setPromptText("Назва напою");
        TextField drinkPriceField = new TextField();
        drinkPriceField.getStyleClass().add("transparent-text-field");
        drinkPriceField.setPromptText("Ціна напою");
        TextField drinkDishesField = new TextField();
        drinkDishesField.getStyleClass().add("transparent-text-field");
        drinkDishesField.setPromptText("Список страв (через кому)");

        Button addDrinkButton = new Button("Додати напій");
        addDrinkButton.getStyleClass().add("addOrderButton");
        addDrinkButton.setOnAction(event -> {
            String name = drinkNameField.getText();
            long price = Long.parseLong(drinkPriceField.getText());
            String dishes = drinkDishesField.getText();
            Item newDrink = new Item(name, price, dishes);
            addDrink(newDrink);
            System.out.println("Додано новий напій: " + name);
            updateDeleteOptions(rightBox);
        });

        VBox addOptionsBox = new VBox();
        addOptionsBox.getChildren().addAll(
                addMealLabel, mealNameField, mealPriceField, mealDishesField, addMealButton,
                addDrinkLabel, drinkNameField, drinkPriceField, drinkDishesField, addDrinkButton
        );

        leftBox.getChildren().add(addOptionsBox);
    }

    public static void updateDeleteOptions(VBox rightBox) {
        rightBox.getChildren().clear();

        VBox deleteOptionsBox = new VBox();
        Label deleteMealLabel = new Label("Видалити страву:");
        deleteMealLabel.getStyleClass().add("meal-name-options");
        deleteOptionsBox.getChildren().add(deleteMealLabel);
        List<Item> meals = Item.readMeals();
        for (Item meal : meals) {
            HBox mealItem = new HBox();
            Label mealLabel = new Label(meal.getName() + " - " + meal.getPrice() + " грн");
            mealLabel.getStyleClass().add("meal-lable-options");
            Button deleteMealButton = new Button("Видалити");

            deleteMealButton.setOnAction(event -> {
                removeMeal(meal);
                System.out.println("Видалено страву: " + meal.getName());
                updateDeleteOptions(rightBox);
            });
            mealItem.getChildren().addAll(mealLabel, deleteMealButton);
            deleteOptionsBox.getChildren().add(mealItem);
        }

        Label deleteDrinkLabel = new Label("Видалити напій:");
        deleteDrinkLabel.getStyleClass().add("meal-name-options");
        deleteOptionsBox.getChildren().add(deleteDrinkLabel);
        List<Item> drinks = Item.readDrinks();
        for (Item drink : drinks) {
            HBox drinkItem = new HBox();
            Label drinkLabel = new Label(drink.getName() + " - " + drink.getPrice() + " грн");
            drinkLabel.getStyleClass().add("meal-lable-options");
            Button deleteDrinkButton = new Button("Видалити");

            deleteDrinkButton.setOnAction(event -> {
                removeDrink(drink);
                System.out.println("Видалено напій: " + drink.getName());
                updateDeleteOptions(rightBox);
            });
            drinkItem.getChildren().addAll(drinkLabel, deleteDrinkButton);
            deleteOptionsBox.getChildren().add(drinkItem);
        }

        rightBox.getChildren().add(deleteOptionsBox);
    }
}
