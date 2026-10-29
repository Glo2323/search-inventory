package com.pluralsight;

import java.io.BufferedReader;

import java.io.FileReader;

import java.util.ArrayList;


public class SearchInventoryApp {


    public static void main(String[] args) {

    getInventory2();


    }
    //!Creating method that as a preloaded list
//    public static void getInventory() {
//
//        ArrayList<Product> products = new ArrayList<>();
//
//        products.add(new Product(3456,"Cordless Drill", 89.99 ));
//        products.add(new Product(5678, "Extension Cord 25in", 24.99));
//        products.add(new Product(6789, "Tape Measure 25ft", 12.49));
//        products.add(new Product(7890, "Safety Glasses", 7.99));
//        products.add(new Product(8901, "Adjustable Wrench", 15.99));
//
//        for (Product p : products) {
//            System.out.printf("%s|%s|%s\n",
//                    p.getProductId(),
//                    p.getProductName(),
//                    p.getProductPrice());
//        }
//
//    }

    public static void  getInventory2() {

        ArrayList<Product> products = new ArrayList<>();
        try {
            //?Creating reader to read my files
            BufferedReader readInventory = new BufferedReader(new FileReader("inventory.csv"));

            //* Variable to hold the products
            String product;

            //?Loop to go through all the products and pull them
            while ((product = readInventory.readLine()) != null) {
                //*Splitting the into parts
                String[] productData = product.split("\\|");
                //*Naming each split part
                int productId = Integer.parseInt(productData[0]);
                String productName = productData[1];
                double productPrice = Double.parseDouble(productData[2]);

                //?Creating the new product from the split parts and adding them to the ArrayList
                products.add(new Product(productId, productName, productPrice));


                //*Displaying the products
                System.out.printf("Product Id: %s\nProduct Name: %s\nProduct Price: %s\n \n",
                        productId,
                        productName,
                        productPrice);

            }


        }
        catch (Exception e)
        {
            System.out.println("Error reading inventory.csv" + e.getMessage());
        }
    }

}
