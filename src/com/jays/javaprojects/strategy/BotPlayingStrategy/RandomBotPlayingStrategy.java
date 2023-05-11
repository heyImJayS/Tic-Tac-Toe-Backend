package com.jays.javaprojects.strategy.BotPlayingStrategy;

import com.jays.javaprojects.models.Board;
import com.jays.javaprojects.models.CellState;
import com.jays.javaprojects.models.Move;
import com.jays.javaprojects.models.Player;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move decideMove(Board board, Player player) {
        for(int i=0; i< board.getBoard().size(); i++){
            for(int j=0; j< board.getBoard().size(); j++){
                if(board.getBoard().get(i).get(j).getCellstate().equals(CellState.EMPTY)){
                    return new Move(player, board.getBoard().get(i).get(j));
                }
            }
        }
        return null;
    }
}
