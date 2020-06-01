package Test;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        Coffee coffee = new Coffee();
        System.out.println("Write action (buy, fill, take, remaining, exit): ");
        while (!exit) {
            exit = coffee.input(scanner.next().toUpperCase());
        }
    }
}

class Coffee {
    boolean exit;
    boolean buy;
    boolean fill;
    int fillCount;
    int water;
    int milk;
    int coffeeBean;
    int cup;
    int money;

    public Coffee() {
        this.water = 400;
        this.milk = 540;
        this.coffeeBean = 120;
        this.cup = 9;
        this.money = 550;
    }

    public boolean input(String str) {
        if (buy) {
            switch (str){
                case "1":
                    if(water-250 < 0){
                        System.out.println("Sorry, not enough water!");
                    }else if(coffeeBean-16 < 0){
                        System.out.println("Sorry, not enough coffee beans!");
                    }else if(cup-1 < 0){
                        System.out.println("Sorry, not enough cup!");
                    }else{
                        System.out.println("I have enough resources, making you a coffee!");
                        water -= 250;
                        coffeeBean -= 16;
                        money += 4;
                        --cup;
                    }
                    break;
                case "2":
                    if(water-350 < 0){
                        System.out.println("Sorry, not enough water!");
                    }else if(milk-75 < 0){
                        System.out.println("Sorry, not enough coffee beans!");
                    }else if(coffeeBean-20 < 0){
                        System.out.println("Sorry, not enough coffee beans!");
                    }else if(cup-1 < 0){
                        System.out.println("Sorry, not enough cup!");
                    }else{
                        System.out.println("I have enough resources, making you a coffee!");
                        water -= 350;
                        milk -= 75;
                        coffeeBean -= 20;
                        money += 7;
                        --cup;
                    }
                    break;
                case "3":
                    if(water-200 < 0){
                        System.out.println("Sorry, not enough water!");
                    }else if(milk-100 < 0){
                        System.out.println("Sorry, not enough coffee beans!");
                    }else if(coffeeBean-12 < 0){
                        System.out.println("Sorry, not enough coffee beans!");
                    }else if(cup-1 < 0){
                        System.out.println("Sorry, not enough cup!");
                    }else{
                        System.out.println("I have enough resources, making you a coffee!");
                        water -= 200;
                        milk -= 100;
                        coffeeBean -= 12;
                        money += 6;
                        --cup;
                    }
            }
            actionMessage();
            buy = false;
        } else if (fill) {
            switch (fillCount) {
                case 0:
                    water += Integer.parseInt(str);
                    System.out.println("Write how many ml of milk do you want to add: ");
                    fillCount++;
                    break;
                case 1:
                    milk += Integer.parseInt(str);
                    System.out.println("Write how many grams of coffee beans do you want to add: ");
                    fillCount++;
                    break;
                case 2:
                    coffeeBean += Integer.parseInt(str);
                    System.out.println("Write how many disposable cups of coffee do you want to add: ");
                    fillCount++;
                    break;
                case 3:
                    cup += Integer.parseInt(str);
                    actionMessage();
                    fill = false;
            }
        }else {
            State state = State.valueOf(str);
            switch (state) {
                case BUY:
                    buy();
                    break;
                case FILL:
                    fill();
                    break;
                case TAKE:
                    take();
                    actionMessage();
                    break;
                case REMAINING:
                    remaining();
                    actionMessage();
                    break;
                case EXIT:
                    exit();
                    break;
            }
        }

        return exit;
    }

    enum State {
        BUY,
        FILL,
        TAKE,
        REMAINING,
        EXIT;
    }

    private void buy() {
        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        buy = true;
    }

    private void fill() {
        System.out.println("\nWrite how many ml of water do you want to add: ");
        fill = true;
    }

    private void take() {
        System.out.println("\nI gave you $"+money);
        money = 0;
    }

    private void remaining() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(water+" of water");
        System.out.println(milk+" of milk");
        System.out.println(coffeeBean+" of coffee beans");
        System.out.println(cup+" of disposable cups");
        String dollar = Integer.toString(money);
        if (money != 0) {
            dollar = "$"+money;
        }
        System.out.println(dollar+" of money");
    }

    private void exit() {
        this.exit = true;
    }

    private void actionMessage() { System.out.println("\nWrite action (buy, fill, take, remaining, exit): "); }
}

