package com.jays.javaprojects.strategy.BotPlayingStrategy;

import com.jays.javaprojects.models.Board;
import com.jays.javaprojects.models.Move;
import com.jays.javaprojects.models.Player;

public interface BotPlayingStrategy {
    public Move decideMove(Board board, Player player);
}
