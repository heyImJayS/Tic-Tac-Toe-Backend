package com.jays.javaprojects.controllers;

import com.jays.javaprojects.exceptions.InvalidGameConstructionParameterException;
import com.jays.javaprojects.models.Game;
import com.jays.javaprojects.models.Player;

import java.util.List;

public class GameController {
    //In this project Game Controller don't do anything
    //Its just calling Game class methods
    public void undo(Game game){
        game.undo();
    }
    public Game createGame(int dimension, List<Player> players) throws InvalidGameConstructionParameterException {
        try {
            return Game.getBuilder()
                    .setDimension(dimension)
                    .setPlayers(players)
                    .build();
        }catch(Exception e){
            return null;
        }
    }
    public void displayGame(Game game){
        game.getBoard().display();
    }
    public boolean executeNextMove(Game game) throws InvalidGameConstructionParameterException {
        return game.makeNextMove();
    }
    public Player getWinner(Game game){
        return game.getWinner();
    }
}
