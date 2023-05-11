package com.jays.javaprojects.strategy.GameWinningStrategy;

import com.jays.javaprojects.models.Board;
import com.jays.javaprojects.models.Cell;
import com.jays.javaprojects.models.Player;

public interface GameWinningStrategy {
    public boolean checkWinner(Board board, Player lastMovePlayer, Cell lastMove );
}
