package com.jays.javaprojects.models;

import com.jays.javaprojects.exceptions.InvalidGameConstructionParameterException;
import com.jays.javaprojects.strategy.GameWinningStrategy.GameWinningStrategy;
import com.jays.javaprojects.strategy.GameWinningStrategy.Type1WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameState gameState;
    private int nextPlayerIndex;
    private Player winner;

    private GameWinningStrategy gameWinningStrategy;

    //If you forget to initialize some attributes
    //Hava a private empty constructor
    private Game(){

    }
    public static Builder getBuilder(){
        return new Builder();
    }

    public void undo(){
        //Remove the last remove last entry in the moves list
        if(!moves.isEmpty()){
            Move nextMove= moves.get(moves.size()-1);
            //Update the Board
            board.getBoard().get(nextMove.getCell().getRow()).get(nextMove.getCell().getCol()).setPlayer(null);
            board.getBoard().get(nextMove.getCell().getRow()).get(nextMove.getCell().getCol()).setCellstate(CellState.EMPTY);
            moves.remove(moves.size()-1);
            Player plr= moves.get(moves.size()-1).getPlayer();
            nextPlayerIndex -=1;
        }

    }
    //Normally these 4 methods makeNextMove, undo, displayBoard, getBuilder
    //generally placed in Game controller, which is good practice
    //But for less complexity in Interviews you can put them in the Game class.
    public boolean makeNextMove() throws InvalidGameConstructionParameterException {
        Player player= players.get(nextPlayerIndex);
        Move newMove= player.decideMove(board);

        int row = newMove.getCell().getRow();
        int col = newMove.getCell().getCol();

        //Now validate the move
        if(row < 0 || row >= board.getBoard().size()){
            System.out.println("Row gone out of bound. Please try again");
            return false;
        }else if(col < 0 || col >= board.getBoard().size()){
            System.out.println("Column gone out of bound. Please try again");
            return false;
        }
        System.out.println("Move happened at: " + row + "," + col);
        board.getBoard().get(row).get(col).setCellstate(CellState.FILLED);
        board.getBoard().get(row).get(col).setPlayer(player);

        Move finalMove= new Move(player, board.getBoard().get(row).get(col));
        this.moves.add(finalMove);

        if(gameWinningStrategy.checkWinner(board, player, board.getBoard().get(row).get(col))){
            gameState=GameState.ENDED;
            winner= players.get(nextPlayerIndex);
        }
        nextPlayerIndex+=1;
        nextPlayerIndex %= players.size();
        return true;
    }

    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }
    //Inner Class Builder
    public static class Builder{
        private int dimension;
        private List<Player> players;

        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }
        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }
        //Now here check the validity
        public boolean valid() throws InvalidGameConstructionParameterException {
            //Dimension >=3
            if(this.dimension < 3){
                throw new InvalidGameConstructionParameterException("Minimum dimension allowed is 3");
            }
            //Players = dimension -1
            if(this.dimension -1 != players.size()){
                throw new InvalidGameConstructionParameterException(" Minimum players = dimension-1 ");
            }

            //No two people can have same symbol
            return true;
        }
        public Game build() throws InvalidGameConstructionParameterException{

            //Check validity 1st
            try{
                valid();
            }catch(Exception e){
                throw new InvalidGameConstructionParameterException(e.getMessage());
            }
            Game game = new Game();
            game.setGameState(GameState.IN_PROGRESS);
            game.setPlayers(players);
            game.setMoves(new ArrayList<>());
            game.setNextPlayerIndex(0);
            game.setBoard(new Board(dimension));
            game.setGameWinningStrategy(new Type1WinningStrategy(dimension));
            return game;
        }
    }
}
