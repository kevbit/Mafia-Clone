import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import Model.Doctor;
import Model.Mafia;
import Model.Player;
import Model.Role;
import Model.Sheriff;
import Model.Vigilante;
import Model.Villager;
import View.GameUI;
import Controller.Game;
import Controller.MafiaMessage;
import Controller.TownMessage;
import Controller.Message;

/**
 * Play: main class to run the game using the Game class which has main logic.
 */
public class Play
{
    static BlockingQueue<Message> queue= new LinkedBlockingQueue<>(); //queue to get messages when buttons clicked.
    public static void main(String[] args) {

        JOptionPane.showConfirmDialog(null,
                "Welcome to Mafia.",
                "Welcome to the town.",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        String numOfPlayers1 = JOptionPane.showInputDialog("Enter number of players.");
        Game.numOfPlayers =Integer.valueOf(numOfPlayers1);

        Game.playerList = new ArrayList<Player>(Game.numOfPlayers);				//Actually creates the playerList A.L
        Game.shuffledList = new ArrayList<Player>(Game.numOfPlayers);				//Initializes the to-be shuffled player A.L
        String userName;
        for(int i = 0; i< Integer.valueOf(Game.numOfPlayers); i++) {
            userName = JOptionPane.showInputDialog("Enter your usernames.");
            Player player = new Player(userName, i);
            Game.playerList.add(i, player);
            Game.shuffledList.add(i, player);
        }

        //code that connects classes in order for UI and game flow to work together
        GameUI ga = new GameUI(queue,Game.playerList);
        ga.ViewRun();
        Game g = new Game(queue,ga);
        g.setUI();
        g.play();
    }
}
