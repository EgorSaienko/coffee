package com.edu.coffee;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Item> items;
    private static final String FILE_PATH = "/com/edu/coffee/ordered.json";
    private LocalTime orderTime;
    private LocalTime completionTime;
    private String name;
    private String number;
    private String type;
    private long total;
    private String list;

    // Constructor initialization
    public Order(LocalTime orderTime, LocalTime completionTime, String name, String number, String type, long total, String list) {
        this.orderTime = orderTime;
        this.completionTime = completionTime;
        this.name = name;
        this.number = number;
        this.type = type;
        this.total = total;
        this.list = list;
        this.items = new ArrayList<>(); // Initialize the items list
    }

    public void addNewItem(Item item) {
        items.add(item);
    }

    public String getList() {
        return list;
    }

    public String getImpotantList() {
        StringBuilder list = new StringBuilder(); // Initialize StringBuilder
        int i = 1;
        for (Item item : items) {
            list.append(i).append(" :").append(item.getName()).append("\n");
            i++;
        }
        return list.toString();
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public Long getTotal() {
        return total;
    }

    public LocalTime getCompletionTime() {
        return completionTime;
    }


    public void setCompletionTime(LocalTime completionTime) {
        this.completionTime = completionTime;
    }

    public static void addOrder(Order order) {
        List<Order> orders = readOrder();
        orders.add(order);
        writeItemsToFile(orders);
    }

    public static void writeItemsToFile(List<Order> orders) {
        JSONObject jsonObject = new JSONObject();
        JSONArray mealsArray = new JSONArray();

        for (Order order : orders) {
            JSONObject itemObject = new JSONObject();
            itemObject.put("orderTime", order.getOrderTime().toString());
            itemObject.put("name", order.getName());
            itemObject.put("number", order.getNumber());
            itemObject.put("type", order.getType());
            itemObject.put("total", order.getTotal());
            itemObject.put("list", order.getList());
            mealsArray.add(itemObject);
        }

        jsonObject.put("Order", mealsArray);

        try (FileWriter file = new FileWriter(getFilePath())) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFilePath() {
        return Order.class.getResource(FILE_PATH).getPath();
    }

    public static List<Order> readOrder() {
        return readItems("Order");
    }

    private static List<Order> readItems(String category) {
        List<Order> orders = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            InputStream inputStream = Order.class.getResourceAsStream(FILE_PATH);

            if (inputStream != null) {
                JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

                JSONArray itemsArray = (JSONArray) jsonObject.get(category);

                if (itemsArray != null) {
                    for (Object obj : itemsArray) {
                        JSONObject itemObject = (JSONObject) obj;
                        String orderTimeStr = (String) itemObject.get("orderTime");
                        LocalTime orderTime = LocalTime.parse(orderTimeStr);
                        String name = (String) itemObject.get("name");
                        String number = (String) itemObject.get("number");
                        String type = (String) itemObject.get("type");
                        Long total = (Long) itemObject.get("total");
                        String list = (String) itemObject.get("list");
                        orders.add(new Order(orderTime, orderTime, name, number, type, total, list));
                    }
                }
            } else {
                System.err.println("Input stream is null. Could not read the file.");
            }
        } catch (IOException e) {
            System.err.println("IOException occurred while reading the file: " + e.getMessage());
        } catch (ParseException e) {
            System.err.println("ParseException occurred while parsing the JSON file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }

        return orders;
    }
}
