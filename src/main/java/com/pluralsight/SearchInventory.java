package com.pluralsight;

import java.util.ArrayList;

public class SearchInventory {
    public static void main(String[] args) {
        ArrayList<String> inventory = getInventory();
        for (int i = 0; i < inventory.size(); i++){
            System.out.println(inventory.get(i));
        }




    }
    public static ArrayList<String> getInventory(){
        ArrayList<String> inventory =new ArrayList<String>();
        inventory.add("4567|10' 2x4 (grade B)|9.99");
        inventory.add("1234|Hammer|19.49");
        inventory.add("2345|Box of nails|9.29");
        return inventory;

    }
}
