import com.jays.javaprojects.controllers.GameController;
import com.jays.javaprojects.exceptions.InvalidGameConstructionParameterException;
import com.jays.javaprojects.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidGameConstructionParameterException {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter the Dimension of the Board: ");
        int dimension= scanner.nextInt();

        System.out.println("Do you need Bot: (Y/N)");
        String needBot= scanner.next();
        int totalPlayers = dimension-1;
        //Just for less complexity we take 1 bot
        if(needBot.equals("Y")){
            totalPlayers = dimension-2;
        }

        List<Player> players= new ArrayList<>();
        for(int i= 1; i<=totalPlayers; i++){
            System.out.println("Enter Player"+i+" Name:");
            String name= scanner.next();

            System.out.println("Enter Player"+i+" Symbol:");
            String symbol= scanner.next();

            players.add(new Player(name,symbol.charAt(0), PlayerType.HUMAN));
        }
        if(needBot.equals("Y")){
            //System.out.println("Enter Player"+i+" Name:");
            String name= "BOT1";

            System.out.println("Enter Player Symbol:");
            String symbol= scanner.next();

            players.add(new Bot(name,symbol.charAt(0), BotDifficultyLevel.EASY));
        }
        GameController gc= new GameController();
        Game game= gc.createGame(dimension, players);

        while(game.getGameState().equals(GameState.IN_PROGRESS)){
            System.out.println("Current Status of the Game:-");
            game.getBoard().display();
            if(gc.executeNextMove(game)){
                System.out.println("Successfully move made");
            }

            System.out.println("Do you want to UNDO (Y/N)");
            String input;
            input= scanner.next();
            if(input.equals("Y")){
                gc.undo(game);
            }
        }
        System.out.println("Game has ended, Result was: ");
        if(!game.getGameState().equals(GameState.DRAW)){
            System.out.println("Winner is : " + gc.getWinner(game).getName());
        }
    }
}