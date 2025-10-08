package com.pluralsight;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchInventory {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        String input;
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/inventory.csv",true); //we're going to append the file
            BufferedWriter buffWriter = new BufferedWriter(fileWriter);


            ArrayList<Product> inventory = getInventory();
            System.out.println("== Welcome to Leomund's Tiny Hardware Hut! == ");
            while(true) {
                System.out.print("What would you like to do?\n[1] Display All Products\n[2] Product ID Look-Up\n[3] Search by Price Range\n[4] Add a New Product\n[5] Exit the Program\n======\nType Here: ");
                int choice = keyboard.nextInt();
                keyboard.nextLine(); //CRLF

                //let's switch!
                switch(choice) {
                    //display all
                    case 1:
                        System.out.println("==== Current Inventory ====");
                        for (int i = 0; i < inventory.size(); i++) {
                            Product product = inventory.get(i);
                            System.out.printf("%d|%s|$%.2f\n", product.getId(), product.getName(), product.getPrice());
                        }
                        System.out.println("===========================");
                        break;
                    //ID Lookup
                    case 2:
                        System.out.println("=== Product Lookup by ID ===\nPlease enter ID: ");
                        int idLookUp= keyboard.nextInt();
                        keyboard.nextLine();//CRLF
                        System.out.printf("Searching for %d...\n",idLookUp);

                        idLookup(inventory,idLookUp);
                        break;
                    //Price Range
                    case 3:
                        System.out.println("=== Lookup by Price Range ===\n");
                        System.out.print("Enter the lowest price: ");
                        double startRange = keyboard.nextDouble();
                        keyboard.nextLine();

                        System.out.print("Enter the highest price: ");
                        double endRange = keyboard.nextDouble();
                        keyboard.nextLine();

                        searchProductByPriceRange(inventory, startRange, endRange);

                        break;
                    //Add new
                    case 4:
                        System.out.println("=== Add a Product ===\n");

                        boolean another = true;
                        while (another) {
                            System.out.print("Enter the 4-digit ID for the Product: ");
                            String newId = keyboard.nextLine();

                            System.out.print("\nEnter the Product Name: ");
                            String newName = keyboard.nextLine();
                            System.out.print("\nEnter the Price: ");
                            String newPrice = keyboard.nextLine();

                            System.out.printf("\nAdding: %s|%s|%s\n",newId,newName,newPrice);
                            buffWriter.newLine();
                            buffWriter.write(newId+"|"+newName+"|"+newPrice);


                            System.out.println("Would you like to add another product to the inventory?\n[Y] Yes [N] No\nType Here: ");
                            String addAgain = keyboard.nextLine().trim().toUpperCase();
                            switch(addAgain){
                                case"Y":
                                    System.out.println("=== Adding Another Product ===");
                                    break;
                                case "N":
                                    System.out.println("== Saved! ==");
                                    buffWriter.close();
                                    inventory = getInventory();
                                    another = false;
                                    break;
                            }
                        }
                        buffWriter.close();

                        break;
                    //Exit
                    case 5:
                        System.out.println("==========\nExiting, Have a Magical D-I-Y-Day!\n==========");
                        System.exit(0);
                        break;

                }
                    System.out.println("===========\nPress [ENTER] to Continue\n===========");






                //list all products



            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }




    }
    public static ArrayList<Product> getInventory(){
        ArrayList<Product> inventory =new ArrayList<Product>();
        try {
            FileReader fileReader = new FileReader("src/main/resources/inventory.csv");
            BufferedReader buffReader = new BufferedReader(fileReader);
            String input;
            //process the file here
            while ((input = buffReader.readLine()) != null) {
                String[] item = input.split("\\|");
                if (!item[0].equals("id")) { //I don't really need this for the current inventory list, but if a new csv is processed with categories it'd help
                    int id = Integer.parseInt(item[0]);
                    String name = item[1];
                    double price = Double.parseDouble(item[2]);
                    Product product = new Product(id,name,price);
                    inventory.add(product);
                }
            }
            buffReader.close();
        } catch (Exception e) {
            System.out.println("Something broke in the method");
            System.out.println(e.getMessage());

        }

        return inventory;
    }
    public static void idLookup(ArrayList<Product> inventory, int idLookUp){
        for (Product product : inventory) {
            if (product.getId() == idLookUp) {
                Product found = new Product(product.getId(), product.getName(), product.getPrice());
                System.out.printf("%d|%s|$%.2f\n", product.getId(), product.getName(), product.getPrice());
                //return found;
            }
        }

    }
    public static void displayProduct(Product product) {
        System.out.printf("%d|%s|$%.2f\n", product.getId(), product.getName(), product.getPrice());
    }
    public static void searchProductByPriceRange(ArrayList<Product> inventory, double startRange, double endRange) {
        for (Product product : inventory) {
            if (startRange <= product.getPrice() && endRange >= product.getPrice()) {
                displayProduct(product);
            }
        }
    }
}
