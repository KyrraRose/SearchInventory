package com.pluralsight;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchInventory {
    Scanner keyboard = new Scanner(System.in);

    String input;
    public static void main(String[] args) {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/inventory.csv",true); //we're going to append the file
            BufferedWriter buffWriter = new BufferedWriter(fileWriter);


            ArrayList<Product> inventory = getInventory();

            //list all products
            System.out.println("==== Current Inventory ====");
            for (int i = 0; i < inventory.size(); i++) {
                Product product = inventory.get(i);
                System.out.printf("%d|%s|$%.2f\n",product.getId(),product.getName(),product.getPrice());
            }
            System.out.println("===========================");




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
    public static void idLookup(ArrayList<Product> inventory){

    }
}
