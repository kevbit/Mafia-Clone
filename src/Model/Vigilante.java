package Model;

import java.util.Scanner;

import javax.swing.*;


/**
 * Vigilante class, will have the logic for shooting a player or themselves. Singleton design pattern used.
 */
public class Vigilante extends Role
{
    int bulletsRemaining; /**will be initialized two 2 as that is how many bullets a vigilante can have*/

    /**
     * singleton instantiation
     * Constructor that makes vigilante a townsperson.
     */
    private Vigilante()
    {
        this.isTown=true;
        bulletsRemaining=2;
    }

    /**
     * Vigilante singleton instantiation
     */
    private static Vigilante instance = new Vigilante();

    /**
     *
     * @return instance of vigilante.
     */
    public static Vigilante getInstance()
    {
        return instance;
    }

    ///**
     //* Prints out a message to Vigilante warning them that they cant shoot on the first day of the game.
   //  */
  /*  public void dayOne()
    {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "You cannot shoot your gun on Day One.",
                "Day One Fam",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE);
    }

*/

    /**
     * Prints out a message to other players that vigilante has shot themselves after killing innocent townsperson.
     */
   public void punishment()
    {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "You cannot stand the fact that you shot an innocent townsperson. You can't live with yourself for much longer because of this...",
                "BIG NEWS",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE);
    }


    /**
     * Depending on whether the vigilante has bullets left or not, the vigilante shoots who they want to, or may choose to not shoot at all
     * @return index of player to be shot, will be -1 if no one is shot.
     */
    public int shoot()
    {
        if(bulletsRemaining>=1)
        {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want to shoot someone tonight?", "Shoot???", dialogButton);
            if(dialogResult == 0) {
                bulletsRemaining--;
                String inputValue = JOptionPane.showInputDialog("Please input the index (number) of player you want to shoot.");
                return Integer.valueOf(inputValue);

            } else {
                int dialogResult1 = JOptionPane.showConfirmDialog(null,
                        "Ooo you decided to be nice today. Cool, your gun is now put away.",
                        "Gun Put Away",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                return -1;
            }
        }
        else
        {
            int dialogResult = JOptionPane.showConfirmDialog(null,
                    "You have run out of bullets. Tough luck. You're just an ordinary villager now.",
                    "OOF",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            return -1;
        }
    }


    /**
     *
     * @return Vigilante string
     */
    public String toString() {
    	return "Vigilante";
    }

    /**
     *
     * @return Vigilante description
     */
    public String description()
    {
        return "You are a member of the town, an ex-town militia man with your own sense of justice and a gun. Every day an innocent\n"
        		+ "townsperson is murdered by the Mafia, your anger grows. Since the law hasn't decided to take them\n"
        		+ "down, you can decide to do it yourself by shooting a player you suspect is a member of the Mafia. But be careful\n"
        		+ "you only have 2 bullets and you always risk shooting an innocent townsperson, and the consequences \n"
        		+ "associated with that are horrendous!\n";
    }



}
