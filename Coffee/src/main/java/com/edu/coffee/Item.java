package com.edu.coffee;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Item class represents a menu item, such as a meal or drink, with a name, price, and description of dishes.
 * This class provides functionality to read and write menu items from a JSON file, and to add or remove items.
 */
public class Item {
    private static final String FILE_PATH = "/com/edu/coffee/complex_lunches.json";
    private String name;
    private long price;
    private String dishes;

    /**
     * Default constructor.
     */
    public Item() {
        this.name = "";
        this.price = 0;
        this.dishes = "";
    }

    /**
     * Parameterized constructor.
     *
     * @param name   the name of the item
     * @param price  the price of the item
     * @param dishes the description of the dishes
     */
    public Item(String name, long price, String dishes) {
        this.name = name;
        this.price = price;
        this.dishes = dishes;
    }

    // Getter and setter methods for name, price, and dishes

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDishes() {
        return dishes;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    /**
     * Adds a meal to the JSON file.
     *
     * @param meal the meal item to be added
     */
    public static void addMeal(Item meal) {
        List<Item> meals = readMeals();
        meals.add(meal);
        List<Item> drinks = readDrinks();
        writeItemsToFile(meals, drinks);
    }

    /**
     * Adds a drink to the JSON file.
     *
     * @param drink the drink item to be added
     */
    public static void addDrink(Item drink) {
        List<Item> drinks = readDrinks();
        drinks.add(drink);
        List<Item> meals = readMeals();
        writeItemsToFile(meals, drinks);
    }

    /**
     * Removes a meal from the JSON file.
     *
     * @param meal the meal item to be removed
     */
    public static void removeMeal(Item meal) {
        List<Item> meals = readMeals();
        meals.removeIf(item -> item.getName().equals(meal.getName()));
        List<Item> drinks = readDrinks();
        writeItemsToFile(meals, drinks);
    }

    /**
     * Removes a drink from the JSON file.
     *
     * @param drink the drink item to be removed
     */
    public static void removeDrink(Item drink) {
        List<Item> drinks = readDrinks();
        drinks.removeIf(item -> item.getName().equals(drink.getName()));
        List<Item> meals = readMeals();
        writeItemsToFile(meals, drinks);
    }

    /**
     * Writes the lists of meals and drinks to the JSON file.
     *
     * @param meals  the list of meal items
     * @param drinks the list of drink items
     */
    private static void writeItemsToFile(List<Item> meals, List<Item> drinks) {
        JSONObject jsonObject = new JSONObject();
        JSONArray mealsArray = new JSONArray();
        JSONArray drinksArray = new JSONArray();

        for (Item item : meals) {
            JSONObject itemObject = new JSONObject();
            itemObject.put("name", item.getName());
            itemObject.put("price", item.getPrice());
            itemObject.put("dishes", item.getDishes());
            mealsArray.add(itemObject);
        }

        for (Item item : drinks) {
            JSONObject itemObject = new JSONObject();
            itemObject.put("name", item.getName());
            itemObject.put("price", item.getPrice());
            itemObject.put("dishes", item.getDishes());
            drinksArray.add(itemObject);
        }

        jsonObject.put("meals", mealsArray);
        jsonObject.put("drinks", drinksArray);

        try (FileWriter file = new FileWriter(getFilePath())) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the file path to the JSON file.
     *
     * @return the file path as a string
     */
    private static String getFilePath() {
        return Item.class.getResource(FILE_PATH).getPath();
    }

    /**
     * Reads the list of meals from the JSON file.
     *
     * @return the list of meal items
     */
    public static List<Item> readMeals() {
        return readItems("meals");
    }

    /**
     * Reads the list of drinks from the JSON file.
     *
     * @return the list of drink items
     */
    public static List<Item> readDrinks() {
        return readItems("drinks");
    }

    /**
     * Reads items from the JSON file based on the specified category (meals or drinks).
     *
     * @param category the category of items to read ("meals" or "drinks")
     * @return the list of items in the specified category
     */
    private static List<Item> readItems(String category) {
        List<Item> items = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            InputStream inputStream = Item.class.getResourceAsStream(FILE_PATH);

            if (inputStream != null) {
                JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(inputStream));

                JSONArray itemsArray = (JSONArray) jsonObject.get(category);

                if (itemsArray != null) {
                    for (Object obj : itemsArray) {
                        JSONObject itemObject = (JSONObject) obj;
                        String name = (String) itemObject.get("name");
                        long price = (long) itemObject.get("price");
                        String dishes = (String) itemObject.get("dishes");
                        items.add(new Item(name, price, dishes));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }
}
