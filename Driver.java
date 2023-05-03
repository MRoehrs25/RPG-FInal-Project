import java.util.HashMap;
import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Driver {
    public static void fightRoom(Room currentRoom, Character car) {
        if (currentRoom.getPow() == car.getPow()) {
            System.out.println("Your character narrowly escapes the room without taking damage");
            currentRoom.setDes();
        }
        else if (currentRoom.getPow() < car.getPow()) {
            System.out.println("You easily clear the room and live to fight another day.");
            currentRoom.setDes();
        }
        else if ((currentRoom.getPow() - 1 == car.getPow()) || currentRoom.getPow() - 2 == car.getPow()) {
            System.out.println("You were not quite strong enough and took a hit clearing this room.");
            currentRoom.setDes();
            car.takeDam();
        }
        else {
            System.out.println("You have failed miserably and now your body shall rot in the basement for forever");
        }
    }
    public static void main (String[] args) {
        Map floor1 = new Map();
        Item brim = new Item("brim", "tears become a lazer, power + 5", 5, 0 );
        Item quad = new Item("quad", "adds 3 other tears when firing, power + 3", 3, 0);
        Item gup = new Item("guppy", "turns characters into guppy the cat, power + 6", 6, 0);
        Item breakfast = new Item("breakfast", "adds 1 health", 0, 1);
        Item mush = new Item("magic mush", "all stats up", 1, 1);
        Item poly = new Item("polyphemus", "mega tears, power + 4", 4, 0);
        Item tech = new Item("tech", "mini lazer tears, power + 1", 1, 0);
        Item bean = new Item("bean", "does absolutly nothing", 0, 0);

        floor1.addItem(brim);
        floor1.addItem(quad);
        floor1.addItem(gup);
        floor1.addItem(breakfast);
        floor1.addItem(mush);
        floor1.addItem(poly);
        floor1.addItem(tech);
        floor1.addItem(bean);

        Room room1 = new Room("Room 1", "This is the starting room. There are exits:", 0);
        Room room2 = new Room("Room 2", "Room full of flies all trying to attack you. There are exits:", 1);
        Room room3 = new Room("Room 3", "Room full of poop, you find 3 coins hidden in the poop. There are exits:", 0);
        Room room4 = new Room("Room 4", "empty room. There are exits:", 0);
        Room room5 = new Room("Room 5", "Full of clotties shooting at you. There are exits:", 2);
        Room room6 = new Room("Item Room", "contains an item. There are exits:", 0);
        Room room7 = new Room("Room 7", "You are chase by Mullibooms. There are exits:", 2);
        Room room8 = new Room("Room 8", "Full of maggots squarming around. There are exits:", 1);
        Room room9 = new Room("Boss Room", "You must defeat Pin to progress. There are exits:", 4);

        room1.addExit("north", "Room 2");
        room1.addEx("north");
        room1.addExit("east", "Room 3");
        room1.addEx("east");
        room1.addExit("south", "Room 4");
        room1.addEx("south");
        room1.addExit("west", "Room 5");
        room1.addEx("west");

        room2.addExit("east", "Item Room");
        room2.addEx("east");
        room2.addExit("south", "Room 1");
        room2.addEx("south");

        room3.addExit("east", "Room 7");
        room3.addEx("east");
        room3.addExit("west", "Room 1");
        room3.addEx("west");

        room4.addExit("north", "Room 1");
        room4.addEx("north");

        room5.addExit("east", "Room 1");
        room5.addEx("east");
        room5.addExit("west", "Room 8");
        room5.addEx("west");

        room6.addExit("west", "Room 2");
        room6.addEx("west");

        room7.addExit("north", "Boss Room");
        room7.addEx("north");
        room7.addExit("west", "Room 3");
        room7.addEx("west");

        room8.addExit("east", "Room 5");
        room8.addEx("east");

        room9.addExit("south", "Room 7");
        room9.addEx("south");

        floor1.addRoom(room1);
        floor1.addRoom(room2);
        floor1.addRoom(room3);
        floor1.addRoom(room4);
        floor1.addRoom(room5);
        floor1.addRoom(room6);
        floor1.addRoom(room7);
        floor1.addRoom(room8);
        floor1.addRoom(room9);

        Scanner scnr = new Scanner(System.in);
        Random rand = new Random();
        int bound = 8;
        Room currentRoom = room1;
        boolean bossFight = false;
        System.out.println("Welcome to the binding of Max");
        System.out.println("You are playing as a character trying to escape his basement filled with different creatures trying to attack you.");
        System.out.println("You can save the game at any time by typing SAVE");
        System.out.println("Lets get started");
        System.out.println("What would you like to name your character?");
        String name = scnr.nextLine();
        Character car = new Character(name);
        while (!bossFight) {
            System.out.println(currentRoom.toString());
            if (currentRoom.getName().equalsIgnoreCase("Boss Room")) {
                bossFight = true;
            }
            else if (currentRoom.getName().equalsIgnoreCase("Item Room")) {
                boolean tem = true;
                if (tem) {
                    System.out.println("You have found the item room");
                    Item it = floor1.getItem(rand.nextInt(bound));
                    System.out.println("You find " + it.getName());
                    System.out.println(it.getName() + " " + it.getDes());
                    System.out.println("Stats:");
                    System.out.println("\tPower: " + it.getPow());
                    System.out.println("\tHealth: " +it.getHealth());
                    tem = false;
                }
                else {
                    System.out.println("You have already found the item in this room.");
                }
            }
            else {
                bossFight = false;
            }
            System.out.println("Exits: ");
            System.out.println(currentRoom.listEx());
            fightRoom(currentRoom, car);
            String input;
            try {
                input = scnr.nextLine();
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid exit");
                input = scnr.nextLine();
            }
            ArrayList<String> exits = currentRoom.getEx();
            boolean bool = true;
            while(bool) {
                if (input.equalsIgnoreCase("save")) {
                    try {
                        String fileName = car.getName() + "GameSave.txt";
                        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                        PrintWriter printWriter = new PrintWriter(fileOutputStream);
                        printWriter.println(car.getName() + ": " + car.getPow() + ", " + car.getHealth());
                        printWriter.println("You were last in " + currentRoom.toString() + " with exits:" + currentRoom.listEx());
                    }
                    catch (IOException e) {
                        System.out.println("An error occurred while creating the file.");
                        e.printStackTrace();
                    }
                break;
                }
                else if (!(exits.contains(input))) {
                    System.out.println("Invalid exit.");
                    System.out.println("Please choose an exit");
                    input = scnr.nextLine();
                }
                else {
                    currentRoom = floor1.getRoom(currentRoom.getExit(input));
                    bool = false;
                    break;
                }
            }
        }
        System.out.println("Congragulations you lived to fight another day.");
        System.our.println("THANKS FOR PLAYING");



    }

}