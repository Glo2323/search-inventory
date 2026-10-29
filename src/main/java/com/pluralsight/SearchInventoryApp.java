package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class SearchInventoryApp {


    public static void main(String[] args) {

        getInventory();


    }

    public static void getInventory() {

        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product(3456,"Cordless Drill", 89.99 ));
        products.add(new Product(5678, "Extension Cord 25in", 24.99));
        products.add(new Product(6789, "Tape Measure 25ft", 12.49));
        products.add(new Product(7890, "Safety Glasses", 7.99));
        products.add(new Product(8901, "Adjustable Wrench", 15.99));

        for (Product p : products) {
            System.out.printf("%s|%s|%s\n",
                    p.getProductId(),
                    p.getProductName(),
                    p.getProductPrice());
        }

    }

    public static void inventoryWriter() {

        ArrayList<Product> products = new ArrayList<>();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("inventory.csv"))) {




        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
