package com.jays.javaprojects.models;

public class Cell {
    private int row;
    private int col;
    private Player player;
    private CellState cellstate;

    public Cell(int row, int col){
        this.row = row;
        this.col= col;
        this.cellstate= CellState.EMPTY;
    }

    public CellState getCellstate() {
        return cellstate;
    }

    public void setCellstate(CellState cellstate) {
        this.cellstate = cellstate;
    }
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
