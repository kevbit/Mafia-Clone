package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.*;
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
 * GameUI class, runs the main panel that has the round number, whether it is day/night, player names with indices, buttons to interact with at the end of the game.
 */
public class GameUI extends JFrame{
    BlockingQueue<Message> queue;

    //Creating static instance variables
    private static final int HEIGHT = 1000; //Height of the UI
    private static final int WIDTH = 1000; //Width of the UI

    //Creates instance variables
    private int buttonCounter = 0;
    private int roundCounter = 0;
    private int phaseCounter = 0;

    private JLabel phase, usernameLabel, roundLabel,congrat;
    //private UpdateHandler updateHandler;
    private JButton mafiaButton;
    private JButton townButton;
    private Container pane;
    BufferedImage mafiaimage;
    JLabel mafialabel;
    BufferedImage townimage;
    JLabel townlabel;
    Message msg;


    private ArrayList<Player> playerList;

    /**
     * Constructor that initializes the panel with round number, whether it is day/night, player names with indices, buttons to interact with at the end of the game.
     * @param queue to store which button has been clicked (used later in the game class
     * @param inputPlayers the list of players and their indices.
     */
    public GameUI( BlockingQueue<Message> queue, ArrayList<Player> inputPlayers)
    {
       this.queue = queue;
        playerList = inputPlayers;
        setTitle("Mafia Game"); //Sets the title of the window
        congrat = new JLabel("Who will win, Town or Mafia?");
        phase = new JLabel("Phase: Day"); //Sample phase
        roundLabel = new JLabel("Round: " + roundCounter); //Sample round
       usernameLabel = new JLabel(generateNames());
      // updateHandler = new UpdateHandler();
        mafiaButton = new JButton("Click me if Mafia wins!");
        townButton = new JButton("Click me if the town wins!");

        mafiaButton.addActionListener (e -> {

            try {
                mafiaimage = ImageIO.read(new File("C:\\Users\\tanya\\IdeaProjects\\TestMafia\\MafiaWins.png"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }



            //will display mafia picture if this button clicked
            mafialabel = new JLabel(new ImageIcon(mafiaimage));
            pane = getContentPane();
            pane.add(mafialabel);
            setSize(WIDTH, HEIGHT);
            setVisible(true);

           // msg = new MafiaMessage("Congrats Mafia");
            try {
                queue.put(new MafiaMessage("Congrats Mafia"));
            } catch (InterruptedException interruptedException) {
            }


        });

        townButton.addActionListener (e -> {
            try {
                townimage = ImageIO.read(new File("TownWins.png"));

            } catch (IOException  ioException) {
                ioException.printStackTrace();
            }

    //will display town wins mage if town wins.
            townlabel = new JLabel(new ImageIcon(townimage));
            pane = getContentPane();
            pane.add(townlabel);
            setSize(WIDTH, HEIGHT);
            setVisible(true);
            //msg = new TownMessage("Congrats Town!");
            try {
                queue.put(new TownMessage("Congrats Town!"));
            } catch (InterruptedException interruptedException) {
            }

        });

    }


    /**
     * Running of the View
     */
    public void ViewRun()
    {

        pane = getContentPane();
        pane.setLayout(new GridLayout(3,0));
        //Adding panes
        pane.add(usernameLabel);
        pane.add(phase);
        pane.add(roundLabel);
        pane.add(mafiaButton);
        pane.add(townButton);
        pane.add(congrat);

        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }
    /**
     * Generates an arraylist of String that represents the names of the players and returns a string representation of it
     * @return
     */
  private String generateNames()
    {
        String s;
        s = "List of Players: ";

        for (Player player: playerList)
        {
            s += player.getIndex() + " " + player.getName()+"  ";
        }
        return s;
    }


    /**
     * updates playerlist on the panel after someone is killed
     * @param inputPlayerList
     */
  public void updatePlayers (ArrayList<Player> inputPlayerList)
  {
      playerList = inputPlayerList;
      usernameLabel.setText(generateNames());
  }

    /**
     * shows the day/night and round number being updated as game goes along.
     */
    public void updateRound ()
    {
        buttonCounter += 1;
        if(buttonCounter % 2 == 1)
        {
            roundCounter += 1;
            phase.setText("Phase: Night");
            phaseCounter = 1;

        }
        else
        {
            phase.setText("Phase: Day");
            phaseCounter = 0;
        }
        roundLabel.setText("Round: " + roundCounter);
    }

    /**
     * Used to update congrats message at the end of the game. Used in controller part of game.
     * @param str
     */
    public void updateCongrats (String str)
    {
        congrat.setText(str);
    }





}
