package com.pluralsight;

import java.io.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class SearchInventoryApp {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        //?Looping the menu screen
        boolean menuApp = true;
        while (menuApp) {
            int command = displayMenu(input);
            //? Switch statement to take user to their selection
            switch (command) {
                case 1 -> getInventory2();
                case 2 -> getProductId();
                case 3 -> getProductPriceRange();
                case 4 -> addProduct();
                case 5 -> menuApp = false;
            }
        }



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

    //!Creating a method that pulling the inventory from a file
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

                Collections.sort(products, Comparator.comparing(Product::getProductName));

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

    //!Creating a method that pulls the product by searching it Id
    public static void getProductId( ) {

        ArrayList<Product> products = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        //*asking for product id
        System.out.println("Enter product Id: ");

        int searchedProductId = input.nextInt();
        try {


            //?Creating reader to read my files
            BufferedReader readInventory = new BufferedReader(new FileReader("inventory.csv"));

            //* Variable to hold the products
            String product;

            while ((product = readInventory.readLine()) != null) {
                //*Splitting the into parts
                String[] productData = product.split("\\|");
                //*Naming each split part
                int productId = Integer.parseInt(productData[0]);
                String productName = productData[1];
                double productPrice = Double.parseDouble(productData[2]);

                //?Creating the new product from the split parts and adding them to the ArrayList
                products.add(new Product(productId, productName, productPrice));


                if (searchedProductId == productId) {
                    System.out.println("=====This is your product====\n");
                    System.out.println("Product Id: " + productId + "\n");
                    System.out.println("Product Name: " + productName + "\n");
                    System.out.println("Product Price: " + productPrice + "\n");
                }


            }

        } catch (Exception e) {
            System.out.println("Error reading inventory.csv" + e.getMessage());
        }
    }

    //!Creating a method that pulls all products in a price range
    public static void getProductPriceRange( ) {

        ArrayList<Product> products = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        //*Asking for there price range
        System.out.println("Enter product price range Ex Min:Max: ");

        //*Storing the price range
        String prices = input.nextLine().trim();

        //*Splitting the price range into a min and a max
        String[] priceRange = prices.split(":");

        double min = Integer.parseInt(priceRange[0]);
        double max = Integer.parseInt(priceRange[1]);

        try {


            //?Creating reader to read my files
            BufferedReader readInventory = new BufferedReader(new FileReader("inventory.csv"));

            //* Variable to hold the products
            String product;

            System.out.println("=====These are the products in your range====\n");

            while ((product = readInventory.readLine()) != null) {
                //*Splitting the into parts
                String[] productData = product.split("\\|");
                //*Naming each split part
                int productId = Integer.parseInt(productData[0]);
                String productName = productData[1];
                double productPrice = Double.parseDouble(productData[2]);

                //?Creating the new product from the split parts and adding them to the ArrayList
                products.add(new Product(productId, productName, productPrice));

                //*Print out products in range
                if (min <= productPrice && productPrice <= max) {
//                    System.out.println("=====These are the products in your range====\n");
                    System.out.println("Product Id: " + productId + "\n");
                    System.out.println("Product Name: " + productName + "\n");
                    System.out.println("Product Price: " + productPrice + "\n\n");
                }


            }

        } catch (Exception e) {
            System.out.println("Error reading inventory.csv" + e.getMessage());
        }
    }


    //!Creating a method to add products to the inventory file
    public static void addProduct( ) {

        try (BufferedWriter productWriter = new BufferedWriter(new FileWriter("inventory.csv", true))){

            Scanner input = new Scanner(System.in);

            //*asking for product id
            System.out.println("Enter product id: ");
           //*Storing id
            int productId = input.nextInt();

            //?eating a line
            input.nextLine();


            //*Asking from product name
            System.out.println("Enter product name: ");
           //*Storing name
            String productName = input.nextLine();

            //*asking for product price
            System.out.println("Enter product price: ");
           //*Storing price
            double productPrice = input.nextDouble();

            //?Formatting product for the inventory file
            String product = String.format("%d|%s|%.2f",  productId, productName, productPrice);
            //*Adding it to the inventory file with a new line
            productWriter.write(product);
            productWriter.newLine();


        }
        catch (Exception e) {
            System.out.println("Error reading inventory.csv" + e.getMessage());
        }



    }

    //!Creating  the menu
    public static int displayMenu(Scanner sC) {


        System.out.println("+++++++++++Menu+++++++++++");

        System.out.println("What do you want to do?\n");

        System.out.println(" 1 - List all products\n");

        System.out.println(" 2 - Lookup a product by its id\n");

        System.out.println(" 3 - Find all products withing a price range\n");

        System.out.println(" 4 - Add a new product\n");

        System.out.println(" 5 - Quit the application\n");

        System.out.println("Enter your command: ");


        //*To catch our command
        return sC.nextInt();
    }

}
