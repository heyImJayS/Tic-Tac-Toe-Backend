package com.jays.javaprojects.models;

import java.util.Scanner;

public class Player {
    private char symbol;
    private String name;
    private PlayerType type;

    public Player(String name, char symbol, PlayerType type){
        this.name= name;
        this.symbol= symbol;
        this.type = type;
    }
    public Move decideMove(Board board){
        System.out.println(this.name+" 's Turn");
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter row# 0-Based indexing: ");
        int row= scanner.nextInt();
        System.out.println("Enter col# 0-Based indexing: ");
        int col= scanner.nextInt();
        return new Move(this, new Cell(row,col));
    }
    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }
}
