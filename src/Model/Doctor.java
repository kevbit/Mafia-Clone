package Model;

import java.util.Scanner;

import javax.swing.*;


/**
 * Doctor class, will have the logic for healing a player or themselves. Singleton design pattern used.
 */
public class Doctor extends Role
{
    private boolean selfHeal;

    /**
     * Singleton Instantiation of constructor.
     */
    private Doctor()
    {
        this.isTown=true;
        selfHeal=true;
    }

    /**
     * Singleton declaration
     */
    private static Doctor instance = new Doctor();

    /**
     *
     * @return singleton doctor object
     */
    public static Doctor getInstance()
    {
        return instance;
    }

    /**
     * This method allows the doctor to heal either themselves or someone else that they choose. However, the Doctor can ony heal themselves once.
     * @return the index of the player that is chosen to be healed.
     */
   public int heal()
    {
        if(selfHeal==true)
        {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want to self heal?", "Self-Heal???", dialogButton);
            if(dialogResult == 0) {
                selfHeal=false;
                String inputValue = JOptionPane.showInputDialog("Please input your own index (player number).");
                return Integer.valueOf(inputValue);

            } else {
                String inputValue1 = JOptionPane.showInputDialog("Please input the index (number) of player you want to heal.");
                return Integer.valueOf(inputValue1);
            }

        }
        else
        {
            String inputValue2 = JOptionPane.showInputDialog("Please input the index (number) of player you want to heal.");
            return Integer.valueOf(inputValue2);
        }
    }

    /**
     *
     * @return Doctor String
     */
    public String toString() {
    	return "Doctor";
    }

    /**
     *
     * @return Doctor's description
     */
    public String description()
    {
        return "You are the doctor of the town, a highly-trained trauma surgeon that can heal even the most gruesome attacks \n"
        		+ " in a single night! Each night, you may choose to heal another player or for one night only, heal yourself,\n "
        		+ "protecting your chosen patient from any potential attacks that would normally mean certain death. You swear\n"
        		+ " to protect the innocent townspeople and work with them to weed out the members\n"
        		+ " of the Mafia.\n"
        		;
    }
    
}
