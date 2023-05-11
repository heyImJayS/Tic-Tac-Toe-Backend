package com.jays.javaprojects.strategy.GameWinningStrategy;

import com.jays.javaprojects.models.Board;
import com.jays.javaprojects.models.Cell;
import com.jays.javaprojects.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Type1WinningStrategy implements GameWinningStrategy{
    private List<HashMap<Character,Integer>> rowSymbolCount = new ArrayList<>();
    private List<HashMap<Character,Integer>> colSymbolCount = new ArrayList<>();
    private HashMap<Character,Integer> topLeftDiagonalSymbolCount = new HashMap<>();
    private HashMap<Character,Integer> topRightDiagonalSymbolCount = new HashMap<>();

    public Type1WinningStrategy(int dimension){
        for(int i=0;i<dimension ;i++){
            rowSymbolCount.add(new HashMap<>());
            colSymbolCount.add(new HashMap<>());
        }
    }

    public boolean isCellOnTopLeftDiagonal(int row, int col){
        return row == col;
    }
    public boolean isCellOnTopRightDiagonal(int row, int col, int dimension){
        return row+col == dimension-1;
    }
    @Override
    public boolean checkWinner(Board board, Player lastMovePlayer, Cell lastMove) {
        int row= lastMove.getRow();
        int col= lastMove.getCol();
        int dimension = board.getBoard().size();
        char symbol= lastMovePlayer.getSymbol();

        //Insert current move into the HashMap
        if(!rowSymbolCount.get(row).containsKey(symbol)){
            rowSymbolCount.get(row).put(symbol,0);
        }
        rowSymbolCount.get(row).put(
                symbol,
                rowSymbolCount.get(row).get(symbol) +1
        );

        if(!colSymbolCount.get(col).containsKey(symbol)){
            colSymbolCount.get(col).put(symbol,0);
        }
        colSymbolCount.get(col).put(
                symbol,
                colSymbolCount.get(col).get(symbol) +1
        );

        if(isCellOnTopLeftDiagonal(row, col)){
            if(!topLeftDiagonalSymbolCount.containsKey(symbol)){
                topLeftDiagonalSymbolCount.put(symbol,0);
            }
            colSymbolCount.get(col).put(
                    symbol,
                    topLeftDiagonalSymbolCount.get(symbol) +1
            );
        }
        if(isCellOnTopRightDiagonal(row, col, board.getBoard().size())){
            if(!topRightDiagonalSymbolCount.containsKey(symbol)){
                topRightDiagonalSymbolCount.put(symbol, 0);
            }
            topRightDiagonalSymbolCount.put(
                    symbol,
                    topRightDiagonalSymbolCount.get(symbol)+1
            );
        }

        //Check if the move was made in the topLeft diagonal
        if(isCellOnTopLeftDiagonal(row, col)  && topLeftDiagonalSymbolCount.get(lastMovePlayer.getSymbol()) == dimension){
            //Present in Top Left diagonal
            return true;
        }
        //Check if move was made in the top right diagonal
        if(isCellOnTopRightDiagonal(row, col, dimension)  && topRightDiagonalSymbolCount.get(lastMovePlayer.getSymbol()) == dimension){
            return true;
        }
        //Check for row
        if(rowSymbolCount.get(row).get(lastMovePlayer.getSymbol()) == dimension){
            return true;
        }
        //Check for col
        if(colSymbolCount.get(col).get(lastMovePlayer.getSymbol()) == dimension){
            return true;
        }
        return false;
    }

}
